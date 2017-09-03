package com.mifinity.demo.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by andrea.schembri on 01/09/2017.
 */
public class AuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(401);
    }
}
