package com.miraclerun.petopia.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "pet")
public class Pet extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @NotNull
    private String name;

    private String intro;



    public Pet() {
    }

    //==생성메서드==//
    @Builder
    public Pet(Member member, String name, String intro) {
        this.member = member;
        this.name = name;
        this.intro = intro;
    }
}
