package com.theZ.dotoring.app.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenResponseDTO {

    private String accessToken;
    private String refreshToken;
}
