package com.theZ.dotoring.app.member.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class MemberJobAndMajorResponseDTO {

    private List<String> jobs;
    private List<String> majors;

    @Builder
    public MemberJobAndMajorResponseDTO(List<String> jobs, List<String> majors) {
        this.jobs = jobs;
        this.majors = majors;
    }
}
