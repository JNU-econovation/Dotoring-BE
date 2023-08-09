package com.theZ.dotoring.app.mento.handler;

import com.theZ.dotoring.app.certification.mapper.CertificationMapper;
import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.certification.service.CertificationService;
import com.theZ.dotoring.app.commonModel.UploadFile;
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
    public void execute(MentoSignupRequestDTO mentoSignupRequestDTO) throws IOException {
        //List<Certification> savedCertifications = certificationService.saveCertifications(certifications);
        UploadFile uploadFile = new UploadFile("멘토_증명서","멘토_증명서20230809958113");
        UploadFile uploadFile1 = new UploadFile("멘토_증명서2","멘토_증명서20230809095932");
        List<UploadFile> uploadFiles = List.of(uploadFile, uploadFile1);
        List<Certification> savedCertifications = CertificationMapper.to(uploadFiles);
        mentoService.saveMento(mentoSignupRequestDTO,savedCertifications);
    }





}
