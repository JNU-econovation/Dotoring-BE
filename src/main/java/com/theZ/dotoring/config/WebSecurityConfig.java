package com.theZ.dotoring.config;

import com.theZ.dotoring.app.auth.*;
import com.theZ.dotoring.app.member.service.MemberManageService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberManageService memberManageService;

    private static final String [] PERMIT_PATH_PATTERN = {
            "/api/member/login","/api/signup-menti","/api/signup-mento","/api/mento/loginId", "/api/member/email", "/api/menti/loginId", "/api/mento/password","/api/menti/password",
            "/auth/re-request","/auth/re-issue"
    };

    /**
     * 정적 자원에 대한 설정
     * @param web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers();
    }

    /**
     *  configure() : HTTP 요청에 대한 설정
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .csrf().disable()
                    // 인증 정보를 세션에 저장하지 않고, 상태가 없는(무상태) 웹 애플리케이션을 구성하기 위해 사용됩니다.
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .formLogin()
                .disable()
                .authorizeRequests()//요청에 대한 인증/인가 규칙을 설정합니다. 즉, 특정한 URL 패턴에 대해 어떤 역할(Role)을 가진 사용자만 접근을 허용할지를 지정합니다.
                    .antMatchers("/api/mento/letter/**").hasRole("MENTO")
                    .antMatchers("/api/mento/room/**").hasRole("MENTO")
                    .antMatchers("/api/menti/letter/**").hasRole("MENTI")
                    .antMatchers("/api/menti/room/**").hasRole("MENTI")
                    .antMatchers("/api/menti","/api/menti/{id}").hasRole("MENTO")
                    .antMatchers("/api/mento","/api/mento/{id}").hasRole("MENTi")
                    .antMatchers("/api/mento/**").hasRole("MENTO")
                    .antMatchers("/api/menti/**").hasRole("MENTI")
                    .antMatchers(PERMIT_PATH_PATTERN).permitAll();
                    // PERMIT_PATH_PATTERN에 해당하는 URL은 모든 사용자에게 접근이 허용됩니다. 즉, 인증된 사용자든 아니든 상관없이 모두 접근할 수 있습니다.

        http.addFilterBefore(jwtAuthenticationFilter(), BasicAuthenticationFilter.class);

        http.exceptionHandling().accessDeniedHandler((request, response, JwtException) -> {
            FilterResponseUtils.handleJwtException(response, new JwtException("비 정상적인 토큰입니다."));
        });
}

    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        customAuthenticationFilter.setFilterProcessesUrl("/member/login");
        customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());
        customAuthenticationFilter.afterPropertiesSet();
        return customAuthenticationFilter;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
        jwtAuthenticationFilter.afterPropertiesSet();
        return jwtAuthenticationFilter;
    }

    /**
     *  bCryptPasswordEncoder() : 비밀번호 암호화
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    public class SecurityFilterManager extends AbstractHttpConfigurer<SecurityFilterManager, HttpSecurity> {
//        @Override
//        public void configure(HttpSecurity builder) throws Exception {
//            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
//            builder.addFilter(new JwtAuthenticationFilter(authenticationManager));
//            super.configure(builder);
//        }
//    }

    @Bean
    public CustomLoginSuccessHandler customLoginSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }

    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider(memberManageService, bCryptPasswordEncoder());
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider());
    }

}