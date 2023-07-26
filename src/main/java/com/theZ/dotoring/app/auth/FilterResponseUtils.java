package com.theZ.dotoring.app.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theZ.dotoring.common.ApiResponse;
import com.theZ.dotoring.common.ApiResponseGenerator;
import com.theZ.dotoring.common.Error;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterResponseUtils {
    public static void handleJwtException(HttpServletResponse resp, JwtException e) throws IOException {
        resp.setStatus(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.toString());
        resp.setContentType("application/json; charset=utf-8");
        ApiResponse.CustomBody customBody = new ApiResponse.CustomBody(false,null, new Error("비 정상적 토큰입니다.", String.valueOf(HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST.toString()));
        ObjectMapper om = new ObjectMapper();
        String responseBody = om.writeValueAsString(customBody);
        resp.getWriter().println(responseBody);
    }

}
