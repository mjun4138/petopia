package com.miraclerun.petopia.request;

import lombok.Getter;

@Getter
public class DeleteMemberRequest {
    private String password;

    public DeleteMemberRequest() {
    }

    public DeleteMemberRequest(String password) {
        this.password = password;
    }
}
