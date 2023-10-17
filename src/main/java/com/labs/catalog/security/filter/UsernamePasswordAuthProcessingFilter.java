package com.labs.catalog.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labs.catalog.dto.LoginRequestRecordDTO;
import com.labs.catalog.exception.BadRequestException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsernamePasswordAuthProcessingFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private final ObjectMapper objectMapper;
    @Autowired
    private final AuthenticationSuccessHandler successHandler;
    @Autowired
    private final AuthenticationFailureHandler failureHandler;

    public UsernamePasswordAuthProcessingFilter(String defaultFilterProcessesUrl, ObjectMapper objectMapper,
                                                   AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler) {
        super(defaultFilterProcessesUrl);
        this.objectMapper = objectMapper;
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        LoginRequestRecordDTO requestDTO = objectMapper.readValue(request.getReader(), LoginRequestRecordDTO.class);
        if(StringUtils.isBlank(requestDTO.username()) || StringUtils.isBlank(requestDTO.password())) {
            throw new BadRequestException("username.password.should.be.provided");
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(requestDTO.username(), requestDTO.password());
        return this.getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
//        super.successfulAuthentication(request, response, chain, authResult);
        this.successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
//        super.unsuccessfulAuthentication(request, response, failed);
        this.failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
