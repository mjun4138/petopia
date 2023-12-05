package com.miraclerun.petopia.repository;

import com.miraclerun.petopia.domain.Member;
import com.miraclerun.petopia.domain.QMember;
import com.miraclerun.petopia.request.GetMembersRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Member> getMembers(GetMembersRequest request) {
        return queryFactory
                .selectFrom(QMember.member)
                .offset(request.getOffset())
                .limit(request.getSize())
                .fetch();
    }

    @Override
    public Long totalMembers() {
        return queryFactory
                .select(QMember.member.count())
                .from(QMember.member)
                .fetchFirst();
    }
}
