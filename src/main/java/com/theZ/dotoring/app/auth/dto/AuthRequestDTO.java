package com.theZ.dotoring.app.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequestDTO {

    private String loginId;
    private String password;
}
