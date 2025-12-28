// package com.example.demo.config;

// import io.jsonwebtoken.*;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;

// import javax.crypto.SecretKey;
// import java.nio.charset.StandardCharsets;
// import java.util.Date;
// import java.util.Set;

// @Component
// public class JwtProvider {

//     @Value("${app.jwtSecret:SecretKeyMustBeLongEnoughForHS512AlgorithmVerification}")
//     private String jwtSecret;

//     @Value("${app.jwtExpirationMs:86400000}")
//     private int jwtExpirationMs;

//     private SecretKey getKey() {
//         return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
//     }

//     public String generateToken(String email, Long userId, Set<String> roles) {
//         return Jwts.builder()
//                 .subject(email)
//                 .claim("userId", userId)
//                 .claim("roles", roles)
//                 .issuedAt(new Date())
//                 .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
//                 .signWith(getKey(), Jwts.SIG.HS512) // Updated for 0.12.x compatibility
//                 .compact();
//     }

//     public String getEmailFromToken(String token) {
//         return Jwts.parser()
//                 .verifyWith(getKey()) // 'verifyWith' is preferred in 0.12.x over setSigningKey
//                 .build()              // <--- THIS IS THE MISSING FIX
//                 .parseSignedClaims(token) // 'parseSignedClaims' is preferred in 0.12.x
//                 .getPayload()
//                 .getSubject();
//     }
    
//     public Long getUserId(String token) {
//         try {
//             Claims claims = Jwts.parser()
//                     .verifyWith(getKey())
//                     .build()          // <--- THIS IS THE MISSING FIX
//                     .parseSignedClaims(token)
//                     .getPayload();
            
//             return claims.get("userId", Long.class);
//         } catch (Exception e) {
//             return null;
//         }
//     }

//     public boolean validateToken(String authToken) {
//         try {
//             Jwts.parser()
//                     .verifyWith(getKey())
//                     .build()          // <--- THIS IS THE MISSING FIX
//                     .parseSignedClaims(authToken);
//             return true;
//         } catch (JwtException | IllegalArgumentException e) {
//             return false;
//         }
//     }
// }

// package com.example.demo.config;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.JwtException;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.stereotype.Component;

// import javax.crypto.SecretKey;
// import java.nio.charset.StandardCharsets;
// import java.util.Date;
// import java.util.Set;
// import java.util.function.Function;

// @Component
// public class JwtProvider {

//     // FIX: This key is now 86 characters long (well over the 64-character/512-bit requirement)
//     // In a production app, you would store this in application.properties
//     private static final String SECRET_STRING = "ThisIsAVeryLongAndSecureStringThatIsGuaranteedToBeAtLeastSixtyFourCharactersLongForHS512Algorithm";

//     private final SecretKey key;
//     private final long JWT_EXPIRATION = 86400000L; // 24 hours

//     public JwtProvider() {
//         // Convert the long string into a standard SecretKey object
//         this.key = Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));
//     }

//     // Generate Token
//     public String generateToken(String email, Long userId, Set<String> roles) {
//         return Jwts.builder()
//                 .subject(email)
//                 .claim("userId", userId)
//                 .claim("roles", String.join(",", roles))
//                 .issuedAt(new Date())
//                 .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
//                 .signWith(key) 
//                 .compact();
//     }

//     // Validate Token
//     public boolean validateToken(String token) {
//         try {
//             Jwts.parser()
//                 .verifyWith(key)
//                 .build()
//                 .parseSignedClaims(token);
//             return true;
//         } catch (JwtException | IllegalArgumentException e) {
//             return false;
//         }
//     }

//     // Get Email from Token
//     public String getEmailFromToken(String token) {
//         return extractClaim(token, Claims::getSubject);
//     }

//     // Get UserId from Token
//     public Long getUserIdFromToken(String token) {
//         Claims claims = extractAllClaims(token);
//         return claims.get("userId", Long.class);
//     }

//     // Helper to extract claims
//     private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//         final Claims claims = extractAllClaims(token);
//         return claimsResolver.apply(claims);
//     }

//     private Claims extractAllClaims(String token) {
//         return Jwts.parser()
//                 .verifyWith(key)
//                 .build()
//                 .parseSignedClaims(token)
//                 .getPayload();
//     }
// }

package com.example.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;
import java.util.function.Function;

@Component
public class JwtProvider {

    // Secure key for HS512 (must be > 64 chars)
    private static final String SECRET_STRING = "ThisIsAVeryLongAndSecureStringThatIsGuaranteedToBeAtLeastSixtyFourCharactersLongForHS512Algorithm";

    private final SecretKey key;
    private final long JWT_EXPIRATION = 86400000L; // 24 hours

    public JwtProvider() {
        this.key = Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));
    }

    // Generate Token
    public String generateToken(String email, Long userId, Set<String> roles) {
        return Jwts.builder()
                .subject(email)
                .claim("userId", userId)
                .claim("roles", String.join(",", roles))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(key)
                .compact();
    }

    // Validate Token
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Get Email
    public String getEmailFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // FIX: Method renamed to 'getUserId' to match Test Suite expectation
    public Long getUserId(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("userId", Long.class);
    }

    // Helper to extract claims
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}