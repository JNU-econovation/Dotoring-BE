package com.theZ.dotoring.app.auth;

import com.theZ.dotoring.app.auth.dto.AuthRequestDTO;
import com.theZ.dotoring.app.member.model.Member;
import com.theZ.dotoring.app.member.service.MemberEmailService;
import com.theZ.dotoring.app.member.service.MemberManageService;
import com.theZ.dotoring.common.ApiResponse;
import com.theZ.dotoring.common.ApiResponseGenerator;
import com.theZ.dotoring.common.MessageCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private MemberManageService memberManageService;


    @GetMapping("/auth/re-request")
    public ApiResponse<ApiResponse.CustomBody> alertExpiriredToken() {
        return ApiResponseGenerator.fail(MessageCode.EXPIRED_TOKEN.getCode(),MessageCode.EXPIRED_TOKEN.getValue(),HttpStatus.OK);
    }

    @PostMapping("/auth/re-issue")
    public ApiResponse<ApiResponse.CustomBody<Void>> reissueTokens(HttpServletRequest request, HttpServletResponse response) {
        // 리프레시 토큰 검증 -> 엑세스 토큰하고 리프레시 토큰을 재발행!
        // 리프레시 토큰이 검증이 안 됬어 -> 로그인 실패
        List<Cookie> refreshTokens = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("refreshToken")).collect(Collectors.toList());
        TokenResponseDTO tokenResponseDTO = memberManageService.makeRefreshToken(refreshTokens.get(0).getValue());
        setResponseHeader(response, tokenResponseDTO);
        return ApiResponseGenerator.success(HttpStatus.OK);
    }


    private void setResponseHeader(HttpServletResponse response, TokenResponseDTO tokenResponseDTO){

        String accessToken = tokenResponseDTO.getAccessToken();
        String refreshToken = tokenResponseDTO.getRefreshToken();

        response.addHeader(AuthConstants.AUTH_HEADER,AuthConstants.TOKEN_TYPE + accessToken);
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60*60*24*7);
        // 쿠키에 만료 날짜를 주지 않으면 세션쿠키가 됨-> 세션 쿠키는 브라우저 종료시 삭제
        response.addCookie(cookie);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }
}
