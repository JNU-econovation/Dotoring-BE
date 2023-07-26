package com.theZ.dotoring.common;

import com.theZ.dotoring.app.auth.AuthConstants;
import com.theZ.dotoring.app.member.model.Member;
import com.theZ.dotoring.app.member.service.MemberManageService;
import io.jsonwebtoken.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import java.util.*;

@Log4j2
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class TokenGenerator {

    private static String secretKey;

    private static long accessTokenExp;

    private static long refreshTokenExp;

    @Value("${jwt.secretKey}")
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Value("${jwt.accessTokenExp}")
    public void setAccessTokenExp(long accessTokenExp) {
        this.accessTokenExp = accessTokenExp;
    }

    @Value("${jwt.refreshTokenExp}")
    public void setRefreshTokenExp(long refreshTokenExp) {
        this.refreshTokenExp = refreshTokenExp;
    }


    @Autowired // 먼저 실행이 되는 애고
    private MemberManageService memberManageService;

    private static MemberManageService staticMemberManageService;

    @PostConstruct // 먼저 실행이 되는 애야!!
    public void init(){
        this.staticMemberManageService = memberManageService;
    }

    public static String generateAccessToken(Member member) { // withExpireAt()은 Date 객체를 써야함으로 claim에 exp로 직접적어줌
        Date validity = new Date(System.currentTimeMillis()
                + accessTokenExp);
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                .setIssuer("Dotoring")
                .setSubject(member.getLoginId())
                .setAudience(member.getDtype())
                .setIssuedAt(new Date())
                .setExpiration(validity)
                .setId(makeUUID())
                .claim("tokenType","accessToken")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    public static String generateRefreshToken(Member member) { // withExpireAt()은 Date 객체를 써야함으로 claim에 exp로 직접적어줌
        Date validity = new Date(System.currentTimeMillis()
                + refreshTokenExp);
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                .setIssuer("Dotoring")
                .setSubject(member.getLoginId())
                .setAudience(member.getDtype())
                .setIssuedAt(new Date())
                .setExpiration(validity)
                .setId(makeUUID())
                .claim("tokenType","refreshToken")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public static String getTokenFromHeader(String header) {
        return header.split(" ")[1];
    }

    public static Claims getClaimsFormToken(String token) {
        token = token.replace(AuthConstants.TOKEN_TYPE, "");
        Object claim = Jwts.parserBuilder()
                .setSigningKey(Base64.getDecoder().decode(secretKey))
                .build()
                .parse(token)
                .getBody();
        return (Claims)claim;
    }

    private static String makeUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static boolean isValidAccessToken(String accessToken) {
        try {
            Claims claims = getClaimsFormToken(accessToken);
            log.info("expireTime :" + claims.getExpiration());
            return true;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (JwtException e) {
            throw new JwtException("잘못된 엑세스 토큰입니다.");
        }
    }

    public static boolean isValidRefreshToken(String refreshToken) {
        try {
            Claims claims = getClaimsFormToken(refreshToken);
            log.info("expireTime :" + claims.getExpiration());
            log.info("email :" + claims.get("email"));
            log.info("role :" + claims.get("role"));
            return true;
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(e.getHeader(), e.getClaims(), "만료된 Refresh 토큰입니다.");
        } catch (JwtException e) {
            throw new JwtException("잘못된 Refresh 토큰입니다.");
        }
    }

    public static String getTokenFromCookies(Cookie[] cookies){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(AuthConstants.AUTH_HEADER)){
                return cookie.getValue();
            }
        }
        return null;
    }

    // 토큰을 받아 클레임을 만들고 권한정보를 빼서 시큐리티 유저객체를 만들어 Authentication 객체 반환
    public static Authentication getAuthentication(String jwt) {
        Claims claims = getClaimsFormToken(jwt);

        UserDetails memberDetails = staticMemberManageService.loadUserByUsername(claims.getSubject());

        return new UsernamePasswordAuthenticationToken(memberDetails,memberDetails.getPassword(), memberDetails.getAuthorities());
    }
}
