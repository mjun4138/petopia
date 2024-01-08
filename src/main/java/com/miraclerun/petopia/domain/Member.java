package com.miraclerun.petopia.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import java.util.List;

@Entity
@Getter @Setter
@Table(name = "member")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Pet> pets;

    @OneToOne(mappedBy = "member", cascade = CascadeType.REMOVE)
    private RefreshToken refreshToken;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String account;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    public Member() {
    }

    //==생성 메서드==//
    @Builder
    public Member(String name, String email, String account, String password) {
        this.name = name;
        this.email = email;
        this.account = account;
        this.password = password;
        this.role = MemberRole.USER;
    }
}
