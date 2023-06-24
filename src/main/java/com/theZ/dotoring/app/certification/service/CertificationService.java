package com.theZ.dotoring.app.certification.service;

import com.theZ.dotoring.app.certification.dto.CertificationRequestDTO;
import com.theZ.dotoring.app.certification.dto.CertificationResponseDTO;
import com.theZ.dotoring.app.certification.mapper.CertificationMapper;
import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.certification.repository.CertificationRepository;
import com.theZ.dotoring.app.common.FileUtils;
import com.theZ.dotoring.app.common.UploadFile;
import com.theZ.dotoring.enums.DeleteStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class CertificationService {

    private final FileUtils fileUtils;
    private final CertificationRepository certificationRepository;

    public CertificationResponseDTO saveCertifications(CertificationRequestDTO certificationRequestDTO) throws IOException {
        List<UploadFile> uploadFiles = fileUtils.storeFiles(certificationRequestDTO.getCertificationNames());
        List<Certification> certifications = CertificationMapper.to(uploadFiles);
        certificationRepository.saveAll(certifications);
        List<String> storeFileNames = certifications.stream()
                .map(Certification::getSaveFileName)
                .collect(Collectors.toList());
        return new CertificationResponseDTO(storeFileNames);
    }

}
