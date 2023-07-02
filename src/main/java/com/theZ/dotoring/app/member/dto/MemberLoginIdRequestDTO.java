package com.theZ.dotoring.app.member.dto;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Pattern;

@Data
@Getter
public class MemberLoginIdRequestDTO {

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,12}$", message = "아이디는 영문과 숫자를 포함한 8~12글자여야 합니다.")
    private String loginId;

}
