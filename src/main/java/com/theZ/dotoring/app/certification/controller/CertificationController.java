package com.theZ.dotoring.app.certification.controller;
import com.theZ.dotoring.app.certification.dto.CertificationRequestDTO;
import com.theZ.dotoring.app.certification.dto.CertificationResponseDTO;
import com.theZ.dotoring.app.certification.service.CertificationService;
import com.theZ.dotoring.common.ApiResponse;
import com.theZ.dotoring.common.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CertificationController {

    private final CertificationService certificationService;

    @PostMapping("/certifications")
    public ApiResponse<ApiResponse.SuccessBody<CertificationResponseDTO>> saveCertifications(@RequestBody CertificationRequestDTO certificationRequestDTO) throws IOException {
        return ApiResponseGenerator.success(certificationService.saveCertifications(certificationRequestDTO), HttpStatus.OK);
    }

}
