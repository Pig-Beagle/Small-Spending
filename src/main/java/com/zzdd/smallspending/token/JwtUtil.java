package com.zzdd.smallspending.token;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    private Key getSigninKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDto generateToken(String userId){
        long expiredTime = 86400000L;
        String accessToken = Jwts.builder()
                .claim("userId", userId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTime))
                .signWith(getSigninKey(), SignatureAlgorithm.HS512)
                .compact();

        long expiredRefreshTime = 1209600000L;
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + expiredRefreshTime))
                .signWith(getSigninKey(), SignatureAlgorithm.HS512)
                .compact();

        return TokenDto.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public TokenDto reissuanceToken(String refreshToken){
        if(!isExpired(refreshToken)){
            String userId = getUserName(refreshToken);

            return generateToken(userId);
        }
        return null;
    }

    public boolean isExpired(String token){
            return Jwts.parserBuilder()
                    .setSigningKey(getSigninKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration()
                    .before(new Date());
    }

    public String getUserName(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("userId", String.class);
    }




}
