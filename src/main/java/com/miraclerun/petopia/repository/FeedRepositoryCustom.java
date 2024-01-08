package com.miraclerun.petopia.repository;

import com.miraclerun.petopia.domain.Feed;

import java.util.List;

public interface FeedRepositoryCustom {

    List<Feed> feedsByPet(Long petId);

    int feedsCountByPet(Long petId);
}
