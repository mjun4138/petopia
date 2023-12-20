package com.miraclerun.petopia.repository;

import com.miraclerun.petopia.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long>, FeedRepositoryCustom {
}
