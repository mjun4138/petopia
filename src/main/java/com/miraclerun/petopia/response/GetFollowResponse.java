package com.miraclerun.petopia.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetFollowResponse {
    private Boolean isFollowed;
    private Long followId;

    public GetFollowResponse() {
    }
    @Builder
    public GetFollowResponse(Boolean isFollowed, Long followId) {
        this.isFollowed = isFollowed;
        this.followId = followId;
    }
}
