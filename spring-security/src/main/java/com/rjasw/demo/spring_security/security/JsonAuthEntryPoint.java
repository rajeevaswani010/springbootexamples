package com.rjasw.demo.spring_security.security;

import java.io.IOException;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JsonAuthEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        super.commence(request, response, authException);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED );
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(Response.ko(authException.getMessage(), HttpStatus.UNAUTHORIZED).toJsonString());
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("API");
        super.afterPropertiesSet();
    }

}
