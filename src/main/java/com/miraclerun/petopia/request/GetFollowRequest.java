package com.miraclerun.petopia.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetFollowRequest {
    private Long followingId;
    private Long followerId;

    public GetFollowRequest() {
    }

    @Builder
    public GetFollowRequest(Long followingId, Long followerId) {
        this.followingId = followingId;
        this.followerId = followerId;
    }
}
