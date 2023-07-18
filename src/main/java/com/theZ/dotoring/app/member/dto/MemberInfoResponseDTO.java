package com.theZ.dotoring.app.member.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Builder
public class MemberInfoResponseDTO {

    private Long memberId;
    private String nickname;
    private String job;
    private String major;
    private List<String> certificationsUrl;

}
