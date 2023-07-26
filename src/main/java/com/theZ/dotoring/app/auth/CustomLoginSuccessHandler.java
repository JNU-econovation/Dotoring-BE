package com.theZ.dotoring.app.auth;

import com.theZ.dotoring.app.member.model.Member;
import com.theZ.dotoring.common.TokenGenerator;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @SneakyThrows
    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                        final Authentication authentication) {
        final Member member = ((MemberDetails) authentication.getPrincipal()).getMember();

        final String accessToken = TokenGenerator.generateAccessToken(member);
        final String refreshToken = TokenGenerator.generateRefreshToken(member);

//        SecurityContextHolder.getContext().setAuthentication(authentication);

        /*
            1. Bearer 공백을 어떻게 넣어야 할지, 2. Cookie에 Key Value가 동시에 들어가는 문제 = 문제가 아님
         */
        response.addHeader(AuthConstants.AUTH_HEADER,AuthConstants.TOKEN_TYPE + accessToken);
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60*60*24*7);
        // 쿠키에 만료 날짜를 주지 않으면 세션쿠키가 됨-> 세션 쿠키는 브라우저 종료시 삭제
        response.addCookie(cookie);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }
}
