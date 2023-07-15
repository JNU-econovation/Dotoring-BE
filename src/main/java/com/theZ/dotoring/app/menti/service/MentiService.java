package com.theZ.dotoring.app.menti.service;

import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.menti.dto.MentiCardResponseDTO;
import com.theZ.dotoring.app.menti.dto.MentiSignupRequestDTO;
import com.theZ.dotoring.app.menti.model.Menti;
import com.theZ.dotoring.app.menti.repository.MentiQueryRepository;
import com.theZ.dotoring.app.menti.repository.MentiRepository;
import com.theZ.dotoring.common.DefaultCondition;
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
    public Slice<MentiCardResponseDTO> findAllMentiBySlice(Long lastMentiId, Integer size, DefaultCondition defaultCondition){
        PageRequest pageRequest = PageRequest.of(0, size);
        Slice<MentiCardResponseDTO> mentiSlice = mentiQueryRepository.findAllBySlice(lastMentiId, defaultCondition, pageRequest);
        return mentiSlice;
    }

}
