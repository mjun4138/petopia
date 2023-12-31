package com.miraclerun.petopia.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginRequest {
    private String account;
    private String password;

    public LoginRequest() {
    }

    @Builder
    public LoginRequest(String account, String password) {
        this.account = account;
        this.password = password;
    }
}
