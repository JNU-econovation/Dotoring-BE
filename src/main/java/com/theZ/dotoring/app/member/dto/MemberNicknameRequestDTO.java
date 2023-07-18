package com.theZ.dotoring.app.member.dto;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Size;

@Data
public class MemberNicknameRequestDTO {

    @Size(min = 3, max = 8, message = "이름은 3자 이상 8자 이하로 입력해주세요.")
    private String nickname;

}
