package com.miraclerun.petopia.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "feed")
public class Feed extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @NotNull
    @Lob
    private String content;

    @NotNull
    private int heartCount;

    public Feed() {
    }

    //==생성 메서드==//
    @Builder
    public Feed(Pet pet, String content) {
        this.pet = pet;
        this.content = content;
        heartCount = 0;
    }

    //==비즈니스 로직==//
    public void addHeart() {
        heartCount += 1;
    }

    public void subHeart() {
        heartCount -= 1;
    }
}
