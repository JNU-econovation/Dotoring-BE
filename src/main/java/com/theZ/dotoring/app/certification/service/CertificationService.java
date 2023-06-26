package com.theZ.dotoring.app.certification.service;

import com.theZ.dotoring.app.certification.dto.CertificationRequestDTO;
import com.theZ.dotoring.app.certification.dto.CertificationResponseDTO;
import com.theZ.dotoring.app.certification.mapper.CertificationMapper;
import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.certification.repository.CertificationRepository;
import com.theZ.dotoring.app.commonModel.FileUtils;
import com.theZ.dotoring.app.commonModel.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CertificationService {

    private final FileUtils fileUtils;
    private final CertificationRepository certificationRepository;

    @Transactional
    public CertificationResponseDTO saveCertifications(CertificationRequestDTO certificationRequestDTO) throws IOException {
        List<UploadFile> uploadFiles = fileUtils.storeFiles(certificationRequestDTO.getCertificationNames());
        List<Certification> certifications = CertificationMapper.to(uploadFiles);
        certificationRepository.saveAll(certifications);
        List<String> storeFileNames = certifications.stream()
                .map(Certification::getSaveFileName)
                .collect(Collectors.toList());
        return new CertificationResponseDTO(storeFileNames);
    }

    public List<Certification> getCertifications(List<String> saveFileNames){
        return certificationRepository.findAllByOrderByCreatedAtDesc().stream()
                .filter(certification -> saveFileNames.contains(certification.getSaveFileName()))
                .collect(Collectors.toList());
    }


}

