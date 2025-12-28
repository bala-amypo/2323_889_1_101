package com.example.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class JwtProvider {

    private static final SecretKey SECRET_KEY =
            Keys.hmacShaKeyFor("my-secret-key-my-secret-key-my-secret-key".getBytes());

    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000; // 1 day

    /* =====================
       TOKEN GENERATION
       ===================== */

    // Used by runtime code
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // Used by TEST CASES
    public String generateToken(String email, Long userId, Set<?> roles) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    /* =====================
       TOKEN PARSING (JJWT 0.12.x)
       ===================== */

    private Claims getClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getEmailFromToken(String token) {
        return getClaims(token).getSubject();
    }

    // Required by TEST CASES
    public Long getUserId(String token) {
        Object value = getClaims(token).get("userId");
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}