package com.theZ.dotoring.app.letter.dto;

import com.theZ.dotoring.app.member.model.Member;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LetterByMemberRequestDTO {

    @NotBlank(message = "빈 칸을 보낼 수 없습니다.")
    @Size(min = 2, max = 200, message = "문자의 최소 길이는 2글자이고, 문자의 최대 길이는 200 글자입니다.")
    private String content;
}