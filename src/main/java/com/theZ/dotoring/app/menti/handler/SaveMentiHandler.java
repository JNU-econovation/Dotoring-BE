package com.theZ.dotoring.app.menti.handler;

import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.certification.service.CertificationService;
import com.theZ.dotoring.app.menti.dto.MentiSignupDTO;
import com.theZ.dotoring.app.menti.service.MentiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SaveMentiHandler {

    private final MentiService mentiService;
    private final CertificationService certificationService;

    @Transactional
    public void execute(MentiSignupDTO mentiSignupDTO){
        List<Certification> certifications = certificationService.getCertifications(mentiSignupDTO.getCertificationIds());
        mentiService.saveMenti(mentiSignupDTO,certifications);
    }
    
}
