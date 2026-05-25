package com.example.AuthService.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {

    private static final String secret="my-super-secret-ket-1233453543@%^&*()_+";
    private final SecretKey key= Keys.hmacShaKeyFor(secret.getBytes());
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }


    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder().setClaims(claims).setSubject(username)
                        .setIssuedAt(new java.util.Date(System.currentTimeMillis()))
                                .setExpiration(new java.util.Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                                        .signWith(SignatureAlgorithm.HS256,
                                                key).compact();
    }

    public String ExtractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(key)   // key should be SecretKey or byte[]
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)   // key should be SecretKey or byte[]
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
        public String validateToken(String token,UserDetails UserDetails) {
        String username = ExtractUsername(token);
        if(username.equals(UserDetails.getUsername()) && !isTokenExpired(token)) {
            return "valid";
    }
        }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new java.util.Date());
    }
}
