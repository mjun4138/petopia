package com.miraclerun.petopia.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateFeedRequest {

    private Long petId;
    private String content;

    public CreateFeedRequest() {
    }

    @Builder
    public CreateFeedRequest(Long petId, String content) {
        this.petId = petId;
        this.content = content;
    }
}
