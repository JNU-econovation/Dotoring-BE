package com.theZ.dotoring.app.menti.handler;

import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.certification.service.CertificationService;
import com.theZ.dotoring.app.menti.dto.MentiSignupRequestDTO;
import com.theZ.dotoring.app.menti.service.MentiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SaveMentiHandler {

    private final MentiService mentiService;
    private final CertificationService certificationService;

    @Transactional
    public void execute(MentiSignupRequestDTO mentiSignupRequestDTO, List<MultipartFile> certifications) throws IOException {
        List<Certification> certificationList = certificationService.saveCertifications(certifications);
        mentiService.saveMenti(mentiSignupRequestDTO,certificationList);
    }
    
}
