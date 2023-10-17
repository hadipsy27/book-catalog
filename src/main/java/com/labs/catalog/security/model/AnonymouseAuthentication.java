package com.labs.catalog.security.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AnonymouseAuthentication extends AbstractAuthenticationToken {

    public AnonymouseAuthentication() {
        super(null);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
//        return super.isAuthenticated();
        return false;
    }
}
