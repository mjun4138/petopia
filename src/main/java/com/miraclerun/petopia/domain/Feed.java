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
@Table(name = "feed")
public class Feed extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @Column(nullable = false)
    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
    private List<Upload> uploads = new ArrayList<>();

    @Column(nullable = false)
    @Lob
    private String content;

    @Column(nullable = false)
    private int heartCount;

    public Feed() {
    }

    //==생성 메서드==//
    @Builder
    public Feed(Pet pet, String content, List<Upload> uploads) {
        setPet(pet);
        this.content = content;
        setUploads(uploads);
        heartCount = 0;
    }

    //==연관관계 메서드==//
    public void setUploads(List<Upload> uploads) {
        this.uploads = uploads;
        for (Upload upload : uploads) {
            upload.setFeed(this);
        }
    }

    public void setPet(Pet pet) {
        this.pet = pet;
        pet.getFeeds().add(this);
    }

    //==비즈니스 로직==//
    public void addHeart() {
        heartCount += 1;
    }

    public void subHeart() {
        heartCount -= 1;
    }
}
