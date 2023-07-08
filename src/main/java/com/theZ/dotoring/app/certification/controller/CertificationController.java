package com.theZ.dotoring.app.certification.controller;
import com.theZ.dotoring.app.certification.dto.CertificationRequestDTO;
import com.theZ.dotoring.app.certification.dto.CertificationResponseDTO;
import com.theZ.dotoring.app.certification.service.CertificationService;
import com.theZ.dotoring.common.ApiResponse;
import com.theZ.dotoring.common.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CertificationController {

    private final CertificationService certificationService;

    @PostMapping("/certifications")
    public ApiResponse<ApiResponse.CustomBody<CertificationResponseDTO>> saveCertifications(@RequestPart List<MultipartFile> certificationNames) throws IOException {
        // todo RequestPart에 대해서 공부
        return ApiResponseGenerator.success(certificationService.saveCertifications(certificationNames), HttpStatus.OK);
    }

}
