package com.theZ.dotoring.app.auth;

import com.theZ.dotoring.common.TokenGenerator;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String accessToken = request.getHeader(AuthConstants.AUTH_HEADER);

        if(accessToken == null){
            chain.doFilter(request,response);
            return;
        }

        String refreshToken = getRefreshToken(request.getCookies());

        if (refreshToken == null) {
            chain.doFilter(request, response);
            return;
        }

        // todo 리프레시 토큰 제발 확인해줘

        boolean validAccessToken = TokenGenerator.isValidAccessToken(accessToken);

        boolean validRefreshToken = TokenGenerator.isValidRefreshToken(refreshToken);

        // 엑세스 토큰이 만료될 경우
        if (!validAccessToken) {
            response.sendRedirect(request.getContextPath() + "/api/auth/re-request");
            chain.doFilter(request, response);
        }

        if(!validAccessToken && !validRefreshToken){
            response.sendRedirect(request.getContextPath() + "/api/auth/fail");
            return;
        }

        // 정상적인 엑세스 토큰일 때 -> decode
        Authentication authentication = TokenGenerator.getAuthentication(accessToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request,response);
    }

    private String getRefreshToken(Cookie[] cookies){
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("refreshToken")) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
