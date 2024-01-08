package com.miraclerun.petopia.service;

import com.miraclerun.petopia.domain.Follow;
import com.miraclerun.petopia.domain.Pet;
import com.miraclerun.petopia.repository.FollowRepository;
import com.miraclerun.petopia.repository.PetRepository;
import com.miraclerun.petopia.request.CreateFollowRequest;
import com.miraclerun.petopia.request.GetFollowRequest;
import com.miraclerun.petopia.response.GetFollowResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FollowService {
    private final PetRepository petRepository;
    private final FollowRepository followRepository;

    /**
     * 팔로우 등록
     */
    @Transactional
    public Long createFollow(CreateFollowRequest request) {
        Pet following = petRepository.findById(request.getFollowing_id())
                .orElseThrow(RuntimeException::new);
        Pet follower = petRepository.findById(request.getFollower_id())
                .orElseThrow(RuntimeException::new);

        Follow follow = Follow.builder()
                .following(following)
                .follower(follower)
                .build();
        followRepository.save(follow);
        following.addFollowing();
        follower.addFollower();

        return follow.getId();
    }

    /**
     * 팔로우 취소
     */
    @Transactional
    public void deleteFollow(Long id) {
        Follow follow = followRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        Pet following = petRepository.findById(follow.getFollowing().getId())
                .orElseThrow(RuntimeException::new);
        Pet follower = petRepository.findById(follow.getFollower().getId())
                .orElseThrow(RuntimeException::new);
        followRepository.delete(follow);
        following.subFollowing();
        follower.subFollower();

    }

    /**
     * 팔로우 조회
     */
    public GetFollowResponse getFollow(GetFollowRequest request) {
        Follow follow = followRepository.getFollow(request);
        if (follow != null) {
            return GetFollowResponse.builder()
                    .isFollowed(true)
                    .followId(follow.getId())
                    .build();
        } else {
            return GetFollowResponse.builder()
                    .isFollowed(false)
                    .build();
        }
    }
}
