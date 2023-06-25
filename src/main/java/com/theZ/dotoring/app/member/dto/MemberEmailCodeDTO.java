package com.theZ.dotoring.app.member.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class MemberEmailCodeDTO {

    private String emailVerificationCode;
}

