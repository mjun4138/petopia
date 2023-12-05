package com.miraclerun.petopia.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateMemberResponse {

    private Long id;

    public CreateMemberResponse() {
    }

    @Builder
    public CreateMemberResponse(Long id) {
        this.id = id;
    }
}
