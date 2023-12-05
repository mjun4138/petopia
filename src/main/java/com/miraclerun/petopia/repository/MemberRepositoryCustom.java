package com.miraclerun.petopia.repository;

import com.miraclerun.petopia.domain.Member;
import com.miraclerun.petopia.request.GetMembersRequest;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> getMembers(GetMembersRequest request);

    Long totalMembers();
}
