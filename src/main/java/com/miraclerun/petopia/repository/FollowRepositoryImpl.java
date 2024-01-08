package com.miraclerun.petopia.repository;

import com.miraclerun.petopia.domain.Follow;
import com.miraclerun.petopia.domain.QFollow;
import com.miraclerun.petopia.request.GetFollowRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FollowRepositoryImpl implements FollowRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Follow getFollow(GetFollowRequest request) {
        return queryFactory
                .selectFrom(QFollow.follow)
                .where(QFollow.follow.follower.id.eq(request.getFollowerId()))
                .where(QFollow.follow.following.id.eq(request.getFollowingId()))
                .fetchFirst();
    }
}
