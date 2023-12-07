package com.miraclerun.petopia.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateFollowRequest {
    private Long following_id;
    private Long follower_id;

    public CreateFollowRequest() {
    }

    @Builder
    public CreateFollowRequest(Long following_id, Long follower_id) {
        this.following_id = following_id;
        this.follower_id = follower_id;
    }
}
