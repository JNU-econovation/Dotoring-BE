package com.theZ.dotoring.app.member.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class MemberNicknameDTO {

    @Size(min = 3, max = 8, message = "이름은 3자 이상 8자 이하로 입력해주세요.")
    private String nickname;

}
