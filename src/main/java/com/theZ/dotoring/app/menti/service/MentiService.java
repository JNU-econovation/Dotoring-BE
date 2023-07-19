package com.theZ.dotoring.app.menti.service;

import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.menti.dto.*;
import com.theZ.dotoring.app.menti.model.Menti;
import com.theZ.dotoring.app.menti.model.MentiFilterCondition;
import com.theZ.dotoring.app.menti.repository.MentiQueryRepository;
import com.theZ.dotoring.app.menti.repository.MentiRepository;
import com.theZ.dotoring.app.mento.dto.MentoAccountUpdateReq;
import com.theZ.dotoring.app.mento.dto.MentoInfoUpdateRequestDTO;
import com.theZ.dotoring.app.mento.dto.MentoSysUpdateReqDTO;
import com.theZ.dotoring.app.mento.model.Mento;
import com.theZ.dotoring.enums.Job;
import com.theZ.dotoring.enums.Major;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class MentiService {

    private final MentiRepository mentiRepository;
    private final MentiQueryRepository mentiQueryRepository;


    public void saveMenti(MentiSignupRequestDTO mentiSignupRequestDTO, List<Certification> certifications){
        Menti menti = Menti.createMenti(mentiSignupRequestDTO.getLoginId(), mentiSignupRequestDTO.getPassword(), mentiSignupRequestDTO.getEmail(), mentiSignupRequestDTO.getNickname(), mentiSignupRequestDTO.getIntroduction(),"basicProfile_47838475947393908393.png",certifications, mentiSignupRequestDTO.getSchool(), mentiSignupRequestDTO.getGrade(), Major.valueOf(mentiSignupRequestDTO.getMajor()), Job.valueOf(mentiSignupRequestDTO.getJob()));
        mentiRepository.save(menti);
    }

    @Transactional(readOnly = true)
    public Menti findMenti(Long mentiId){
        Menti menti = mentiRepository.findById(mentiId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 멘티입니다."));
        return menti;
    }

    @Transactional(readOnly = true)
    public Slice<MentiCardResponseDTO> findAllMentiBySlice(Long lastMentiId, Integer size, MentiFilterCondition mentiFilterCondition){
        PageRequest pageRequest = PageRequest.of(0, size);
        Slice<MentiCardResponseDTO> mentiSlice = mentiQueryRepository.findAllBySlice(lastMentiId, mentiFilterCondition, pageRequest);
        return mentiSlice;
    }

    @Transactional
    public void updateMentiInfo(MentiInfoUpdateRequestDTO updateRequest, Menti menti) {
        if (updateRequest.getIntroduction() != null) {
            menti.updateIntroduction(updateRequest.getIntroduction());
        }

        if (updateRequest.getSchool() != null) {
            menti.updateSchool(updateRequest.getSchool());
        }

        if (updateRequest.getProfileImage() != null) {
            menti.updateProfileImage(updateRequest.getProfileImage());
        }

        if (updateRequest.getJob() != null) {
            // Job이 잘못된 경우 핸들링
            Job.getJob(updateRequest.getJob().toString());

            menti.updateJob(updateRequest.getJob());
        }

        if (updateRequest.getGrade() != null) {
            menti.updateGrade(updateRequest.getGrade());
        }

        if (updateRequest.getMajor() != null) {
            // Major가 잘못된 경우 핸들링
            Major.getMajor(updateRequest.getMajor().toString());

            menti.updateMajor(updateRequest.getMajor());
        }

        // 부모 트랜잭션에서 findMento 해서 영속 엔티티를 자식 트랜잭션에게 인자로 넘겨줬다면 여전히 영속성에 있을까? -> 더티 체킹으로 테스팅해보기
        // 아래 코드 없이 더티 체킹 되면, 영속성이 남아있는 것
        // 아래 코드 없이 더티 체킹 된다. -> 영속성이 남아있다.
        //mentoRepository.save(mento);
    }

    @Transactional
    public void updateMentoSys(MentiSysUpdateReqDTO mentiSysUpdateReqDTO, Menti menti) {
        menti.updatePreferredMentoring(mentiSysUpdateReqDTO.getPreferredMentoring());
    }

    @Transactional
    public void updateMentoAccount(MentiAccountUpdateReq mentiAccountUpdateReq, Menti menti) {
        if (mentiAccountUpdateReq.getLoginId() != null){
            menti.updateloginId(mentiAccountUpdateReq.getLoginId());
        }

        if (mentiAccountUpdateReq.getPassword() != null){
            menti.updatePwd(mentiAccountUpdateReq.getPassword());
        }
    }

}
