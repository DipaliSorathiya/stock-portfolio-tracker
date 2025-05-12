package com.tech.stockmarket.stock_market_backend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // ✅ Replace this with a valid Base64-encoded secret
    private final String secretKey = "dC0g1LMynqAPVm0EtQYTxCT/ly+RW9NTWfPaKbfXU8=";

    // ✅ Generate a Key object from the string
    private final Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

    private final long expirationMs = 1000 * 60 * 60 * 10; // 10 hours

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256) // ✅ Secure way to sign
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key) // ✅ Must use the same key object
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}