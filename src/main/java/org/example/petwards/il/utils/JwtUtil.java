package org.example.petwards.il.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.example.petwards.dl.entities.Wizard;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Component
public class JwtUtil {

    private final byte[] secret = "yabadabadoooooooooooooooooooooooooooooo".getBytes();
    private final JwtParser parser;

    public JwtUtil() {
        this.parser = Jwts.parser().verifyWith(this.getSecretKey()).build();
    }

    private SecretKey getSecretKey() {
        return new SecretKeySpec(secret, "HmacSHA256");
    }

    public String generateToken(Wizard wizard) {
        long expireAfter = 96 * 60 * 60 * 1000L;
        return Jwts.builder()
                .subject(wizard.getUsername())
                .claim("id", wizard.getId())
                .claim("role", wizard.getShelterRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expireAfter))
                .signWith(this.getSecretKey())
                .compact();
    }

    private Claims getClaims(String token) {
        return this.parser.parseSignedClaims(token).getPayload();
    }

    public String getUsername(String token) {
        return this.getClaims(token).getSubject();
    }

    public Long getUserId(String token) {
        return this.getClaims(token).get("id", Long.class);
    }

    public String getRole(String token) {
        return this.getClaims(token).get("role", String.class);
    }

    public boolean isValid(String token) {
        Claims claims = getClaims(token);
        Date now = new Date();
        return now.after(claims.getIssuedAt()) && now.before(claims.getExpiration());
    }
}