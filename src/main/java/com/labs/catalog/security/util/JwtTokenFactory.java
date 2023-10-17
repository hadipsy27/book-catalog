package com.labs.catalog.security.util;

import com.labs.catalog.security.model.AccessJWTToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@AllArgsConstructor
public class JwtTokenFactory {

    private final Key secret;

    public AccessJWTToken createAccessJWTToken(String username, Collection<? extends GrantedAuthority> authorities) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("scopes", authorities.stream().map(a -> a.getAuthority()).collect(Collectors.toList()));
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime expiredDate = currentTime.plusMinutes(15);

        Date currentTimeDate = Date.from(currentTime.atZone(ZoneId.of("Asia/Jakarta")).toInstant());
        Date expiredTimeDate = Date.from(expiredDate.atZone(ZoneId.of("Asia/Jakarta")).toInstant());

        String token = Jwts.builder().setClaims(claims)
                .setIssuer("http://github.com/hadipsy27").setIssuedAt(currentTimeDate)
                .setExpiration(expiredTimeDate)
                .signWith(secret, SignatureAlgorithm.HS256).compact();

        return new AccessJWTToken(token, claims);
    }
}
