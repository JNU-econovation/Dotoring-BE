package com.theZ.dotoring.app.certification.dto;

import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Getter
public class CertificationRequestDTO {

    private List<MultipartFile> certificationNames;
    // 첫 번째는 재직 증명서
    // 두 번째는 졸업 증명서
    // 프론트에서 List 순서대로 줄 것
    // 회원가입 심사 절차에서 순서도 확인해야함

}
