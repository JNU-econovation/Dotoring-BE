package com.theZ.dotoring.app.mento.service;
import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.menti.model.Menti;
import com.theZ.dotoring.app.mento.dto.*;
import com.theZ.dotoring.app.mento.model.Mento;
import com.theZ.dotoring.app.mento.repository.MentoQueryRepository;
import com.theZ.dotoring.app.mento.repository.MentoRepository;
import com.theZ.dotoring.app.mento.model.MentoFilterCondition;
import com.theZ.dotoring.enums.Job;
import com.theZ.dotoring.enums.Major;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class MentoService {

    private final MentoRepository mentoRepository;
    private final MentoQueryRepository mentoQueryRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void saveMento(MentoSignupRequestDTO mentoSignupRequestDTO, List<Certification> certifications){
        Mento mento = Mento.createMento(mentoSignupRequestDTO.getLoginId(), bCryptPasswordEncoder.encode(mentoSignupRequestDTO.getPassword()), mentoSignupRequestDTO.getEmail(), mentoSignupRequestDTO.getNickname(), mentoSignupRequestDTO.getIntroduction(),"basicProfile_47838475947393908393.png",certifications, mentoSignupRequestDTO.getCompany(), mentoSignupRequestDTO.getCareerLevel(), Job.valueOf(mentoSignupRequestDTO.getJob()), Major.valueOf(mentoSignupRequestDTO.getMajor()));
        mentoRepository.save(mento);
    }

    @Transactional(readOnly = true)
    public Mento findMento(Long mentoId){
        Mento mento = mentoRepository.findById(mentoId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 멘토입니다."));
        return mento;
    }

    @Transactional(readOnly = true)
    public Mento findMentoByEmail(String email){
        Mento mento = mentoRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("존재하지 않는 멘토입니다."));
        return mento;
    }

    @Transactional(readOnly = true)
    public String findMentoLoginId(String email){
        Mento mento = mentoRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("존재하지 않는 멘토입니다."));
        return mento.getLoginId();
    }
    
    @Transactional(readOnly = true)
    public Slice<MentoCardResponseDTO> findAllMentoBySlice(Long lastMentoId, Integer size, MentoFilterCondition mentoFilterCondition){
        PageRequest pageRequest = PageRequest.of(0, size);
        Slice<MentoCardResponseDTO> mentoSlice = mentoQueryRepository.findAllBySlice(lastMentoId, mentoFilterCondition, pageRequest);
        return mentoSlice;
    }

    @Transactional
    public void updateMentoInfo(MentoInfoUpdateRequestDTO updateRequest, Mento mento) {
        if (updateRequest.getIntroduction() != null) {
            mento.updateIntroduction(updateRequest.getIntroduction());
        }

        if (updateRequest.getCompany() != null) {
            mento.updateCompany(updateRequest.getCompany());
        }

        if (updateRequest.getProfileImage() != null) {
            mento.updateProfileImage(updateRequest.getProfileImage());
        }

        if (updateRequest.getJob() != null) {
            // Job이 잘못된 경우 핸들링
            Job.getJob(updateRequest.getJob().toString());

            mento.updateJob(updateRequest.getJob());
        }

        if (updateRequest.getCareerLevel() != null) {
            mento.updateCareerLevel(updateRequest.getCareerLevel());
        }

        if (updateRequest.getMajor() != null) {
            // Major가 잘못된 경우 핸들링
            Major.getMajor(updateRequest.getMajor().toString());

            mento.updateMajor(updateRequest.getMajor());
        }

        // 부모 트랜잭션에서 findMento 해서 영속 엔티티를 자식 트랜잭션에게 인자로 넘겨줬다면 여전히 영속성에 있을까? -> 더티 체킹으로 테스팅해보기
        // 아래 코드 없이 더티 체킹 되면, 영속성이 남아있는 것
        // 아래 코드 없이 더티 체킹 된다. -> 영속성이 남아있다.
        //mentoRepository.save(mento);
    }

    @Transactional
    public void updateMentoSys(MentoSysUpdateReqDTO mentoSysUpdateReqDTO, Mento mento) {
        mento.updateMentoringSystem(mentoSysUpdateReqDTO.getMentoringSystem());
    }

    @Transactional
    public void updateMentoAccount(MentoAccountUpdateReq mentoAccountUpdateReq, Mento mento) {
        if (mentoAccountUpdateReq.getLoginId() != null){
            mento.updateloginId(mentoAccountUpdateReq.getLoginId());
        }

        if (mentoAccountUpdateReq.getPassword() != null){
            mento.updatePwd(mentoAccountUpdateReq.getPassword());
        }
    }
}
