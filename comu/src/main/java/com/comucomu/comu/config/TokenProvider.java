package com.comucomu.comu.config;

import com.comucomu.comu.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProperties jwtProperties;

    public String generateToken(User user, Duration expiredAt){
        Date now = new Date();

        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
    }

    // JWT 토큰 생성
    private String makeToken(Date expiry, User user){
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // header type : JWT
                .setIssuer(jwtProperties.getIssuer())   // payload issuer : 발급자
                .setIssuedAt(now)   // payload iat : 현재시간
                .setExpiration(expiry)  // payload exp : expiry 멤버 변수값
                .setSubject(user.getNickname())    // payload sub : user nickname
                .claim("id", user.getId())       // payload id : user id
                // 서명 : secret_key와 같이 HS256 방식으로 암호와
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    // JWT 토큰 유효성 검증 메서드
    public boolean validToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(jwtProperties.getSecretKey())    // secret_key 로 복호화
                    .build()
                    .parseClaimsJws(token);

            return true;
        }catch (Exception e){   // 복호화 과정 중 에러 발생
            return false;   // 유효하지 않은 토큰으로 판단
        }
    }

    // 토큰 기반으로 인증 정보 get
    public UsernamePasswordAuthenticationToken getAuthentication(String token){
        Claims claims = getClaims(token);

        Set<SimpleGrantedAuthority> authorities 
                = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        
        return new UsernamePasswordAuthenticationToken(
                new org.springframework.security.core.userdetails.User(
                        claims.getSubject(), "", authorities), token, authorities);
    }

    // 토큰 기반으로 user id get
    public String getUserId(String token){
        Claims claims = getClaims(token);

        return claims.get("id", String.class);
    }

    private Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(jwtProperties.getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
