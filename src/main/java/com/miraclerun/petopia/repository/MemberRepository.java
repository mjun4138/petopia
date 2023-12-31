package com.miraclerun.petopia.repository;

import com.miraclerun.petopia.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    Optional<Member> findByAccount(String account);
}
