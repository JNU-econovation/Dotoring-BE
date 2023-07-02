package com.theZ.dotoring.app.menti.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MentiCardResponseDTO {

    private Long mentoId;
    private String profileImage;
    private String nickname;
    private String job;
    private String major;
    private String introduction;

}
