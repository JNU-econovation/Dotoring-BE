package com.theZ.dotoring.app.menti.service;

import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.menti.dto.MentiSignupDTO;
import com.theZ.dotoring.app.menti.model.Menti;
import com.theZ.dotoring.app.menti.repository.MentiRepository;
import com.theZ.dotoring.enums.Major;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MentiService {

    private final MentiRepository mentiRepository;
    @Transactional
    public void saveMenti(MentiSignupDTO mentiSignupDTO, List<Certification> certifications){
        Menti menti = Menti.createMenti(mentiSignupDTO.getLoginId(), mentiSignupDTO.getPassword(), mentiSignupDTO.getEmail(), mentiSignupDTO.getNickname(), mentiSignupDTO.getIntroduction(), certifications, mentiSignupDTO.getSchool(), mentiSignupDTO.getGrade(), Major.valueOf(mentiSignupDTO.getMajor()));
        mentiRepository.save(menti);
    }

}
