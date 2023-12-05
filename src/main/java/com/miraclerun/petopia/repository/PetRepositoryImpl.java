package com.miraclerun.petopia.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PetRepositoryImpl implements PetRepositoryCustom{
    private final JPAQueryFactory queryFactory;
}
