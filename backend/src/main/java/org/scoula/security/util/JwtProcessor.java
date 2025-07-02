package org.scoula.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtProcessor {

    //헤더, 페이로드, 서명
    static private final long TOKEN_VALID_MILISECOND = 1000L * 60 * 30; // 5 분
    private String secretKey = "qwerty12345qwerty12345qwerty12345qwerty12345qwerty12345qwerty12345qwerty12345"; //충분한 긴 임의의(랜덤한) 비밀키 문자열 배정;
    private Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));


    // private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);-- 운영시 사용

    // JWT 생성
    public String generateToken(String subject){
        //헤더, 페이로드, 서명
        return Jwts.builder()

                // 페이로드
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + TOKEN_VALID_MILISECOND))
                // 페이로드

                // 서명
                .signWith(key)
                .compact();
                // 서명
    }
    // JWT Subject(username) 추출- 해석 불가인 경우 예외 발생
    // 예외 ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException


    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    // JWT 검증(유효기간 검증) - 해석 불가인 경우 예외 발생
    // refresh 토근을 검증할 때 사용
    public boolean validateToken(String token) {
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        return true;
    }
}
