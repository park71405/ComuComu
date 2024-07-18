package com.comucomu.comu.DTO.Login;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateAccessTokenResponse {

    private String accessToken;

    public CreateAccessTokenResponse(String token) {
        this.accessToken = token;
    }
}
