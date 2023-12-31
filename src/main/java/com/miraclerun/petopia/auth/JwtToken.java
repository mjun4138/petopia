package com.miraclerun.petopia.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
public class JwtToken {

    private String grantType;
    private String accessToken;
    private String refreshToken;

    @Builder
    public JwtToken(String grantType, String accessToken, String refreshToken) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}
