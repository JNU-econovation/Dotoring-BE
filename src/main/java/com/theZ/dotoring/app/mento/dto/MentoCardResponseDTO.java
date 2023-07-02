package com.theZ.dotoring.app.mento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class MentoCardResponseDTO {

    private Long mentoId;
    private String profileImage;
    private String nickname;
    private String job;
    private String major;
    private String introduction;

}
