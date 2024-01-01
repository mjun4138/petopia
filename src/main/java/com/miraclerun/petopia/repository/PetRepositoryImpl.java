package com.miraclerun.petopia.repository;

import com.miraclerun.petopia.domain.Pet;
import com.miraclerun.petopia.domain.QMember;
import com.miraclerun.petopia.domain.QPet;
import com.miraclerun.petopia.domain.QUpload;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PetRepositoryImpl implements PetRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Pet> petsByMember(Long memberId) {
        return queryFactory
                .selectFrom(QPet.pet)
                .leftJoin(QPet.pet.petUpload).fetchJoin()
                .where(QPet.pet.member.id.eq(memberId))
                .fetch();
    }
}
