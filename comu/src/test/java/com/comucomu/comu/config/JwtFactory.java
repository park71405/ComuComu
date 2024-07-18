package com.comucomu.comu.config;


import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Builder;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Getter
public class JwtFactory {

    private String subject = "유저1";
    private Date issuedAt = new Date();
    private Date expiration = new Date(new Date().getTime() + Duration.ofDays(14).toMillis());
    private Map<String, Object> claims = Collections.emptyMap();

    // 빌더 패턴 사용
    @Builder
    public JwtFactory(String subject, Date issuedAt, Date expiration, Map<String, Object> claims){
        this.subject = subject != null ? subject : this.subject;
        this.issuedAt = issuedAt != null ? issuedAt : this.issuedAt;
        this.expiration = expiration != null ? expiration : this.expiration;
        this.claims = claims != null ? claims : this.claims;
    }

    public static JwtFactory withDefaultValues(){
        return JwtFactory.builder().build();
    }

    // jjwt lib 사용해 jwt 토큰 생성
    public String createToken(JwtProperties jwtProperties){
        Date now = new Date();

        Key key = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject(subject)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .addClaims(claims)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

}
