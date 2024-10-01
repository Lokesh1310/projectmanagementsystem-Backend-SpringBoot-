package com.pms.config;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {
    static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 hours
                .claim("email", auth.getName())
                // Uncomment if you want to add authorities to the token
                // .claim("authorities", auth.getAuthorities().toString())
                .signWith(key)
                .compact();
    }

    public static String getEmailFromToken(String jwt) {
       
            jwt = jwt.substring(7);


        Claims claims = Jwts.parserBuilder()
                            .setSigningKey(key)
                            .build()
                            .parseClaimsJws(jwt)
                            .getBody();

        return String.valueOf(claims.get("email"));
    }
}