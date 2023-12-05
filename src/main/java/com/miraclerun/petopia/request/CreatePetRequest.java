package com.miraclerun.petopia.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreatePetRequest {

    private Long memberId;
    private String name;
    private String intro;

    public CreatePetRequest() {
    }

    @Builder
    public CreatePetRequest(Long memberId, String name, String intro) {
        this.memberId = memberId;
        this.name = name;
        this.intro = intro;
    }
}
