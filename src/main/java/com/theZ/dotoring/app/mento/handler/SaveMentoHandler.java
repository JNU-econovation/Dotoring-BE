package com.theZ.dotoring.app.mento.handler;

import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.certification.service.CertificationService;
import com.theZ.dotoring.app.mento.dto.MentoSignupDTO;
import com.theZ.dotoring.app.mento.service.MentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SaveMentoHandler {

    private final MentoService mentoService;
    private final CertificationService certificationService;

    @Transactional
    public void execute(MentoSignupDTO mentoSignupDTO){
        List<Certification> certifications = certificationService.getCertifications(mentoSignupDTO.getStoreFileNames());
        mentoService.saveMento(mentoSignupDTO,certifications);
    }

}
