package com.miraclerun.petopia.repository;

import com.miraclerun.petopia.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long>, FollowRepositoryCustom {
}
