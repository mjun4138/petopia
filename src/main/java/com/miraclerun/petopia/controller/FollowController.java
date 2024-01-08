package com.miraclerun.petopia.controller;

import com.miraclerun.petopia.request.CreateFollowRequest;
import com.miraclerun.petopia.request.GetFollowRequest;
import com.miraclerun.petopia.response.CreateFollowResponse;
import com.miraclerun.petopia.response.GetFollowResponse;
import com.miraclerun.petopia.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FollowController {
    private final FollowService followService;

    /**
     * 팔로우
     */
    @PostMapping("/follows")
    public CreateFollowResponse createFollow(
            @RequestBody CreateFollowRequest request
    ) {
        Long followId = followService.createFollow(request);

        return CreateFollowResponse.builder()
                .id(followId)
                .build();
    }

    /**
     * 팔로우 취소
     */
    @DeleteMapping("/follows/{followId}")
    public void deleteFollow(
            @PathVariable(name = "followId") Long followId
    ) {
        followService.deleteFollow(followId);
    }

    /**
     * 팔로우 조회
     */
    @GetMapping("/follows")
    public GetFollowResponse getFollow(
            @RequestBody GetFollowRequest request
    ) {
        return followService.getFollow(request);
    }
}
