package com.theZ.dotoring.app.mento.service;
import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.menti.model.Menti;
import com.theZ.dotoring.app.mento.dto.MentoCardResponseDTO;
import com.theZ.dotoring.app.mento.dto.MentoSignupRequestDTO;
import com.theZ.dotoring.app.mento.mapper.MentoMapper;
import com.theZ.dotoring.app.mento.model.Mento;
import com.theZ.dotoring.app.mento.repository.MentoQueryRepository;
import com.theZ.dotoring.app.mento.repository.MentoRepository;
import com.theZ.dotoring.common.DefaultCondition;
import com.theZ.dotoring.enums.Job;
import com.theZ.dotoring.enums.Major;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MentoService {

    private final MentoRepository mentoRepository;
    private final MentoQueryRepository mentoQueryRepository;


    @Transactional
    public void saveMento(MentoSignupRequestDTO mentoSignupRequestDTO, List<Certification> certifications){
        Mento mento = Mento.createMento(mentoSignupRequestDTO.getLoginId(), mentoSignupRequestDTO.getPassword(), mentoSignupRequestDTO.getEmail(), mentoSignupRequestDTO.getNickname(), mentoSignupRequestDTO.getIntroduction(),"basicProfile_47838475947393908393.png",certifications, mentoSignupRequestDTO.getCompany(), mentoSignupRequestDTO.getCareerLevel(), Job.valueOf(mentoSignupRequestDTO.getJob()), Major.valueOf(mentoSignupRequestDTO.getMajor()));
        mentoRepository.save(mento);
    }

    public Mento findMento(Long mentoId){
        Mento mento = mentoRepository.findById(mentoId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 멘토입니다."));
        return mento;
    }

    public Slice<MentoCardResponseDTO> findAllMentoBySlice(Long lastMentoId, Integer size, DefaultCondition defaultCondition){
        PageRequest pageRequest = PageRequest.of(0, size);
        Slice<Mento> mentoSlice = mentoQueryRepository.findAllBySlice(lastMentoId, defaultCondition, pageRequest);
        List<MentoCardResponseDTO> mentoCardResponseDTOList = MentoMapper.from(mentoSlice.getContent());
        return new SliceImpl<>(mentoCardResponseDTOList,pageRequest,mentoSlice.hasNext());
    }


}
