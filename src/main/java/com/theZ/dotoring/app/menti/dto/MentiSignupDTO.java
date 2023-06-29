package com.theZ.dotoring.app.menti.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MentiSignupDTO {

    private String school;
    private Long grade;
    private String major;
    private List<Long> certificationIds;
    @Size(min = 3, max = 8, message = "이름은 3자 이상 8자 이하로 입력해주세요.")
    private String nickname;

    @Size(min = 10, max = 100)
    private String introduction;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,12}$", message = "아이디는 영문과 숫자를 포함한 8~12글자여야 합니다.")
    private String loginId;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{7,12}$", message = "비밀번호는 영문, 숫자, 특수문자를 포함한 7~12글자여야 합니다.")
    private String password;

    @Email(message = "이메일 패턴이 올바르지 않습니다.")
    private String email;
}
