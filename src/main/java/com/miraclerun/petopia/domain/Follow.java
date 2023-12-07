package com.miraclerun.petopia.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "follow", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"following_id", "follower_id"})
})
public class Follow {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private Pet following;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private Pet follower;

    public Follow() {
    }

    //==생성 메서드==//
    @Builder
    public Follow(Pet following, Pet follower) {
        this.following = following;
        this.follower = follower;
    }

}
