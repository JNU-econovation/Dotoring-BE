package com.theZ.dotoring.app.certification.service;

import com.theZ.dotoring.app.certification.dto.CertificationResponseDTO;
import com.theZ.dotoring.app.certification.mapper.CertificationMapper;
import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.certification.repository.CertificationRepository;
import com.theZ.dotoring.common.FileUtils;
import com.theZ.dotoring.app.commonModel.UploadFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CertificationService {

    private final FileUtils fileUtils;
    private final CertificationRepository certificationRepository;

    public List<Certification> saveCertifications(List<MultipartFile> certifications) throws IOException {
        List<UploadFile> uploadFiles = fileUtils.storeFiles(certifications);
        List<Certification> certificationList = CertificationMapper.to(uploadFiles);
        return certificationList;
    }

    public List<Certification> getCertifications(List<Long> certificationIds){
        return certificationRepository.findAllByOrderByCreatedAtDesc().stream()
                .filter(certification -> certificationIds.contains(certification.getId()))
                .collect(Collectors.toList());
    }
}

