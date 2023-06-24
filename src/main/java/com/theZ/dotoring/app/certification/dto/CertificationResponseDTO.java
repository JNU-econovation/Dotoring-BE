package com.theZ.dotoring.app.certification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
@AllArgsConstructor
public class CertificationResponseDTO {

    private List<String> storeFileName;

}
