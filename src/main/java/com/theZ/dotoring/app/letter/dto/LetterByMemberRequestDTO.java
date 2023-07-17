package com.theZ.dotoring.app.letter.dto;

import com.theZ.dotoring.app.member.model.Member;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LetterByMemberRequestDTO {

    @NotBlank
    @Max(200)
    private String content;
}