package com.miraclerun.petopia.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateMemberRequest {

    private String email;
    private String name;
    private String account;
    private String password;

    public CreateMemberRequest() {
    }

    @Builder
    public CreateMemberRequest(String email, String name, String account, String password) {
        this.email = email;
        this.name = name;
        this.account = account;
        this.password = password;
    }
}
