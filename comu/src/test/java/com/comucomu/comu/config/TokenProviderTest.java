package com.comucomu.comu.config;

import com.comucomu.comu.Repository.UserRepository;
import com.comucomu.comu.Service.UserDetailService;
import com.comucomu.comu.Service.UserService;
import com.comucomu.comu.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
public class TokenProviderTest {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @DisplayName("generateToken 토큰 생성 테스트")
    @Test
    void generateToken(){
        // given
        User testUser = userService.findById("user1");

        // when
        String token = tokenProvider.generateToken(testUser, Duration.ofDays(14));

        // then
        Key key = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));

        String userId = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("id", String.class);

        Assertions.assertEquals(testUser.getId(), userId);
    }

    @DisplayName("validToken 만료된 토큰일 경우에 대한 유효성 검증")
    @Test
    void validToken_invalidToken(){
        // given
        String token = JwtFactory.builder()
                .expiration(new Date(new Date().getTime() - Duration.ofDays(7).toMillis()))
                .build()
                .createToken(jwtProperties);

        // when
        boolean result = tokenProvider.validToken(token);

        // then
        Assertions.assertFalse(result);
    }

    @DisplayName("getAuthentication 토큰으로 인증 정보 획득")
    @Test
    void getAuthentication(){
        // given
        String userEmail = "user@gmail.com";
        String token = JwtFactory.builder()
                .subject(userEmail)
                .build()
                .createToken(jwtProperties);

        // when
        Authentication authentication = tokenProvider.getAuthentication(token);

        // then
        Assertions.assertEquals( ((UserDetails) authentication.getPrincipal()).getUsername(), userEmail );
    }

    @DisplayName("getUserId 토큰으로 유저 ID 획득")
    @Test
    void getUserId(){
        // given
        String userId = "user1";

        String token = JwtFactory.builder()
                .claims(Map.of("id", userId))
                .build()
                .createToken(jwtProperties);

        // when
        String userIdByToken = tokenProvider.getUserId(token);

        // then
        Assertions.assertEquals(userId, userIdByToken);
    }

}
