package com.miraclerun.petopia.request;

import lombok.Getter;

@Getter
public class DeletePetRequest {
    private String password;

    public DeletePetRequest() {
    }

    public DeletePetRequest(String password) {
        this.password = password;
    }
}
