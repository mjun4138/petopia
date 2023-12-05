package com.miraclerun.petopia.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreatePetResponse {

    private Long id;

    public CreatePetResponse() {
    }

    @Builder
    public CreatePetResponse(Long id) {
        this.id = id;
    }
}
