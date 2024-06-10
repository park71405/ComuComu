package com.comucomu.comu.Controller;

import com.comucomu.comu.DTO.CreateAccessTokenRequest;
import com.comucomu.comu.DTO.CreateAccessTokenResponse;
import com.comucomu.comu.Service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenApiController {

    private final TokenService tokenService;

    @PostMapping("/api/token")
    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken
            (@RequestBody CreateAccessTokenRequest request){

        // 리프레시 토큰을 받아 새로운 액세스 토큰을 전달
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccessTokenResponse(newAccessToken));
    }

}
