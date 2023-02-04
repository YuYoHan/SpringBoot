package com.example.demo3.jwt;

import io.jsonwebtoken.*;
import lombok.Data;
import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Log4j2
public class JwtProvider {

    private static final String JWT_SECRET = "secretKey";

    // 토큰 유효 시간
    private static final int JWT_EXPIRATION_MS = 604800000;

    // JWT 토큰 생성
    public static  String generateToken(Authentication authentication) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_MS);

        return Jwts.builder()
                // 사용자
                .setSubject((String) authentication.getPrincipal())
                // 현재 시간 기반으로 생성
                .setIssuedAt(new Date())
                // 만료 시간 세팅
                .setExpiration(expiryDate)
                .claim("userId", "zxzz45")
                .claim("userName", "김민주")
                // 사용할 암호화 알고리즘, signature에 들어갈 secret 값 세팅
                .signWith(SignatureAlgorithm.ES512, JWT_SECRET)
                .compact();
    }

    // JWT 토큰에서 아이디 추출
    public static String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        log.info("id : " + claims.getId());
        log.info("issuer : " + claims.getIssuer());
        log.info("issue : " + claims.getIssuedAt().toString());
        log.info("subject : " + claims.getSubject());
        log.info("Audience : " + claims.getAudience());
        log.info("expire : " + claims.getExpiration().toString());
        log.info("userName : " + claims.get("userName"));

        return claims.getSubject();
    }

    // JWT 토큰 유효성 검사
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJwt(token);
        } catch (SignatureException e) {
            log.error("Invalid JWT signature", e);
        } catch (MalformedJwtException e ) {
            log.error("Invalid JWT token", e);
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token", e);
        } catch (IllegalStateException e) {
            log.error("JWT claims string is empty", e);
        }
        return false;
    }
}
