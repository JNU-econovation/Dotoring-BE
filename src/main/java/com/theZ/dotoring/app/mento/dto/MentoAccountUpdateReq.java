package com.theZ.dotoring.app.mento.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class MentoAccountUpdateReq {

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,12}$", message = "아이디는 영문과 숫자를 포함한 8~12글자여야 합니다.")
    private String loginId;

    @Size(min = 7, max = 12)
    private String password;

    public MentoAccountUpdateReq(String password) {
        this.password = password;
    }
}
