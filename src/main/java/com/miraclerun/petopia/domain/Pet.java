package com.miraclerun.petopia.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "pet")
public class Pet extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToOne(mappedBy = "pet", orphanRemoval = true)
    private PetUpload petUpload;

    @OneToMany(mappedBy = "pet")
    private List<Feed> feeds;

    @Column(nullable = false)
    private String name;

    @Lob
    private String intro;

    @Column(nullable = false)
    private int following;

    @Column(nullable = false)
    private int follower;


    public Pet() {
    }

    //==생성메서드==//
    @Builder
    public Pet(Member member, String name, String intro, PetUpload petUpload) {
        this.member = member;
        this.name = name;
        this.intro = intro;
        following = 0;
        follower = 0;
        if (petUpload != null) {
            setPetUpload(petUpload);
        }
        feeds = new ArrayList<>();
    }

    //==연관관계 메서드==//
    public void petUpload(PetUpload petUpload) {
        this.petUpload = petUpload;
        petUpload.setPet(this);
    }

    //==비즈니스 로직==//
    public void addFollowing() {
        following += 1;
    }

    public void addFollower() {
        follower += 1;
    }

    public void subFollowing() {
        following -= 1;
    }

    public void subFollower() {
        follower -= 1;
    }

}
