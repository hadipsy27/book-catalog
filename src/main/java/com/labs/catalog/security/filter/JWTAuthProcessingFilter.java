package com.labs.catalog.security.filter;

import com.labs.catalog.security.model.AnonymouseAuthentication;
import com.labs.catalog.security.model.JWTAuthenticationToken;
import com.labs.catalog.security.model.RawAccessJWTToken;
import com.labs.catalog.util.TokenExtractor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final TokenExtractor tokenExtractor;
    private final AuthenticationFailureHandler failureHandler;

    public JWTAuthProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher,
                                      TokenExtractor tokenExtractor, AuthenticationFailureHandler failureHandler) {
        super(requiresAuthenticationRequestMatcher);
        this.tokenExtractor = tokenExtractor;
        this.failureHandler = failureHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String authorizationHeader = request.getHeader("Authorization");
        String jwt = tokenExtractor.extract(authorizationHeader);
        RawAccessJWTToken rawToken = new RawAccessJWTToken(jwt);
        return this.getAuthenticationManager().authenticate(new JWTAuthenticationToken(rawToken));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
        super.successfulAuthentication(request, response, chain, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        SecurityContextHolder.getContext().setAuthentication(new AnonymouseAuthentication());

        failureHandler.onAuthenticationFailure(request, response, failed);
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
