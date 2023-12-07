package com.miraclerun.petopia.repository;

import com.miraclerun.petopia.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long>, FollowRepositoryCustom {
}
