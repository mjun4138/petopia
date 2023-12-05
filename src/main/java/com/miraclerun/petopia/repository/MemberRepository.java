package com.miraclerun.petopia.repository;

import com.miraclerun.petopia.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

}
