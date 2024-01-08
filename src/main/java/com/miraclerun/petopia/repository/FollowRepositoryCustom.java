package com.miraclerun.petopia.repository;

import com.miraclerun.petopia.domain.Follow;
import com.miraclerun.petopia.request.GetFollowRequest;

public interface FollowRepositoryCustom {
    Follow getFollow(GetFollowRequest request);
}
