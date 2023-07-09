package com.theZ.dotoring.app.letter.dto;

import com.theZ.dotoring.app.member.model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LetterByMemberRequestDTO {
    private String content;
}