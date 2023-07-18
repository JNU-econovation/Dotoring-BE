package com.theZ.dotoring.app.mento.handler;

import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.certification.service.CertificationService;
import com.theZ.dotoring.app.mento.dto.MentoSignupRequestDTO;
import com.theZ.dotoring.app.mento.service.MentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SaveMentoHandler {

    private final MentoService mentoService;
    private final CertificationService certificationService;

    @Transactional
    public void execute(MentoSignupRequestDTO mentoSignupRequestDTO, List<MultipartFile> certifications) throws IOException {
        List<Certification> savedCertifications = certificationService.saveCertifications(certifications);
        mentoService.saveMento(mentoSignupRequestDTO,savedCertifications);
    }

}
