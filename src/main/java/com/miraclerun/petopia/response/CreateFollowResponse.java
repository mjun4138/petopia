package com.miraclerun.petopia.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateFollowResponse {
    private Long id;

    public CreateFollowResponse() {
    }

    @Builder
    public CreateFollowResponse(Long id) {
        this.id = id;
    }
}
