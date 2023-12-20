package com.miraclerun.petopia.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateFeedResponse {

    private Long id;

    public CreateFeedResponse() {
    }

    @Builder
    public CreateFeedResponse(Long id) {
        this.id = id;
    }
}
