package com.miraclerun.petopia.repository;

import com.miraclerun.petopia.domain.Feed;
import com.miraclerun.petopia.domain.QFeed;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FeedRepositoryImpl implements FeedRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Feed> feedsByPet(Long petId) {
        return queryFactory
                .selectFrom(QFeed.feed)
                .join(QFeed.feed.pet).fetchJoin()
                .where(QFeed.feed.pet.id.eq(petId))
                .orderBy(QFeed.feed.pet.createdAt.desc())
                .fetch();
    }

    @Override
    public int feedsCountByPet(Long petId) {
        return Math.toIntExact(queryFactory
                .select(QFeed.feed.count())
                .from(QFeed.feed)
                .where(QFeed.feed.pet.id.eq(petId))
                .fetchFirst());
    }
}
