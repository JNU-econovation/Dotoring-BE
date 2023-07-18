package com.theZ.dotoring.app.member.dto;


import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Email;

@Data
public class MemberEmailRequestDTO {


    @Email(message = "이메일 패턴이 올바르지 않습니다.")
    private String email;

}
