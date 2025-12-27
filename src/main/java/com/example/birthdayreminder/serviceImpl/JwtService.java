package com.example.birthdayreminder.serviceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "u8fRz5L1s2Jt9vO6x1y7mP0qW3cA5bX9";

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // 🔹 Generate JWT
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 10)
                ) // 10 hours
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔹 Extract email (subject) from JWT
    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    // 🔹 Check if token is expired
    public boolean isTokenExpired(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    // 🔹 Validate token
    public boolean isTokenValid(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 🔹 Internal claim parser
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
