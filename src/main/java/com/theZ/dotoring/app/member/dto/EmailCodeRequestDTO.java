package com.theZ.dotoring.app.member.dto;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class EmailCodeRequestDTO {

    private String code;
    @Email
    private String email;
}
