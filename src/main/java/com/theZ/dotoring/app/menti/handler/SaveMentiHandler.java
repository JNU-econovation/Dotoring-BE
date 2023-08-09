package com.theZ.dotoring.app.menti.handler;

import com.theZ.dotoring.app.certification.mapper.CertificationMapper;
import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.certification.service.CertificationService;
import com.theZ.dotoring.app.commonModel.UploadFile;
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
    public void execute(MentiSignupRequestDTO mentiSignupRequestDTO) throws IOException {
        //List<Certification> certificationList = certificationService.saveCertifications(certifications);
        UploadFile uploadFile = new UploadFile("멘티_증명서","멘티_증명서20230809959132");
        List<UploadFile> uploadFiles = List.of(uploadFile);
        List<Certification> certifications = CertificationMapper.to(uploadFiles);
        mentiService.saveMenti(mentiSignupRequestDTO,certifications);
    }
    
}
