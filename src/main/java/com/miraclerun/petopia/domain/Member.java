package com.miraclerun.petopia.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

@Entity
@Getter @Setter
@Table(name = "member")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Column(unique = true)
    private String account;

    @NotNull
    private String password;

    public Member() {
    }

    //==생성 메서드==//
    @Builder
    public Member(String name, String email, String account, String password) {
        this.name = name;
        this.email = email;
        this.account = account;
        this.password = password;
    }
}
