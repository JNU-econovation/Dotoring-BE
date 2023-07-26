package com.theZ.dotoring.app.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theZ.dotoring.app.menti.model.Menti;
import com.theZ.dotoring.app.mento.model.Mento;
import com.theZ.dotoring.exception.InputNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public CustomAuthenticationFilter(final AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) {
        final UsernamePasswordAuthenticationToken authRequest;

        /*
            isMento : true, false
        */

        boolean isMento = Boolean.parseBoolean(request.getHeader("isMento"));
        try{
            if(isMento){
                final Mento mento = new ObjectMapper().readValue(request.getInputStream(), Mento.class);
                authRequest = new UsernamePasswordAuthenticationToken(mento.getLoginId(), mento.getPassword());
            }else {
                final Menti menti = new ObjectMapper().readValue(request.getInputStream(), Menti.class);
                authRequest = new UsernamePasswordAuthenticationToken(menti.getLoginId(), menti.getPassword());
            }
        } catch (IOException exception){
            throw new InputNotFoundException();
        }

        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }


}

