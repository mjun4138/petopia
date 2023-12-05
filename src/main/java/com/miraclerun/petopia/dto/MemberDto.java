package com.miraclerun.petopia.dto;

import com.miraclerun.petopia.domain.Member;
import lombok.Getter;

@Getter
public class MemberDto {
    private Long id;
    private String email;
    private String name;
    private String account;
    private String createdAt;

    public MemberDto() {
    }

    public MemberDto(Member member) {
        id = member.getId();
        email = member.getEmail();
        name = member.getName();
        account = member.getAccount();
        createdAt = member.getCreatedAt();
    }
}
