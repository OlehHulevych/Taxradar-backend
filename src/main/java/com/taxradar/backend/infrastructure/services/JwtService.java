package com.taxradar.backend.infrastructure.services;

import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;


@Service
public class JwtService {
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    public String generateToken(UserDetails userDetails){
        return Jwts.builder().subject(userDetails.getUsername()).
                issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey())
                .compact();
    }

    public String extractUsername(String token){
        return Jwts.parser().verifyWith(getSignInKey()).build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
    public long getExpirationTime() {
        return jwtExpiration;
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        String username = extractUsername(token);
        Date expiration = Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(token)
                .getPayload()
                .getExpiration();
        return username.equals(userDetails.getUsername()) && expiration.after(new Date());
    }

    private SecretKey getSignInKey(){
        byte [] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
