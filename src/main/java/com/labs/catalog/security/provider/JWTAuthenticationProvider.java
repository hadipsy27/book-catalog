package com.labs.catalog.security.provider;

import com.labs.catalog.security.model.JWTAuthenticationToken;
import com.labs.catalog.security.model.RawAccessJWTToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JWTAuthenticationProvider implements AuthenticationProvider {

    private Key key;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RawAccessJWTToken token = (RawAccessJWTToken) authentication.getPrincipal();
        Jws<Claims> jwsClaims = token.parseClaims(key);
        String subject = jwsClaims.getBody().getSubject();
        List<String> scopes = jwsClaims.getBody().get("scopes", List.class);
        List<GrantedAuthority> authorities = scopes.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return authorities;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return subject;
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };

        return new JWTAuthenticationToken(userDetails, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JWTAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
