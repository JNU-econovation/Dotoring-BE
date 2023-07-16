package com.theZ.dotoring.app.letter.dto;

import com.theZ.dotoring.app.member.model.Member;
import lombok.*;

import javax.validation.constraints.Max;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LetterByMemberRequestDTO {

    @NonNull
    @Max(200)
    private String content;
}