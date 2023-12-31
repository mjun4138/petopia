package com.miraclerun.petopia.auth;

import com.miraclerun.petopia.domain.Member;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class UserPrincipal extends User {

    private final Long memberId;

    public UserPrincipal(Member member) {
        super(member.getAccount(), member.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_" + member.getRole().toString())));
        this.memberId = member.getId();
    }
}
