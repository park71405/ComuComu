package com.comucomu.comu.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
public class CreateAccessTokenResponse {

    private String accessToken;

    public CreateAccessTokenResponse(String token) {
        this.accessToken = token;
    }
}
