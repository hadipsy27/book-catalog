package com.labs.catalog.security.filter;

import com.labs.catalog.security.model.RawAccessJWTToken;
import com.labs.catalog.util.TokenExtractor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final TokenExtractor tokenExtractor;

    protected JWTAuthProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher, TokenExtractor tokenExtractor) {
        super(requiresAuthenticationRequestMatcher);
        this.tokenExtractor = tokenExtractor;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String authorizationHeader = request.getHeader("Authorization");
        String jwt = tokenExtractor.extract(authorizationHeader);
        RawAccessJWTToken rawToken = new RawAccessJWTToken(jwt);
        return null;
    }
}
