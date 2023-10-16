package com.labs.catalog.security.model;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.security.Key;

public class RawAccessJWTToken implements Token{

    private String token;

    public RawAccessJWTToken(String token) {
        super();
        this.token = token;
    }

    public Jws<Claims> parseClaims(Key signinKey){
        return Jwts.parserBuilder().setSigningKey(signinKey).build().parseClaimsJws(token);
    }

    @Override
    public String getToken() {
        return this.token;
    }
}
