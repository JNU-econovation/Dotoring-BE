package com.theZ.dotoring.app.member.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class MemberEmailCodeResponseDTO {

    private String emailVerificationCode;
}

