package com.theZ.dotoring.app.letter.dto;

import com.theZ.dotoring.app.letter.domain.Letter;
import com.theZ.dotoring.app.member.model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LettersAndMemberDTO {

    private List<Letter> letterList;

    private Member member;
}
