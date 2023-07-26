package com.theZ.dotoring.app.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
public class MemberEmailCodeRequestDTO {

    @Email
    private String email;
    private String emailVerificationCode;
}
