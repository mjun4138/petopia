package com.miraclerun.petopia.dto;

import com.miraclerun.petopia.domain.Pet;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PetDto {

    private Long id;
    private Long memberId;
    private String name;
    private String intro;
    private int feedCount;
    private int following;
    private int follower;
    private String createdAt;
    private String modifiedAt;
    private String profileImage;

    public PetDto() {
    }

    public PetDto(Pet pet) {
        id = pet.getId();
        memberId = pet.getMember().getId();
        name = pet.getName();
        intro = pet.getIntro();
        following = pet.getFollowing();
        follower = pet.getFollower();
        if (pet.getFeeds() != null) {
            feedCount = pet.getFeeds().size();
        } else {
            feedCount = 0;
        }
        createdAt = pet.getCreatedAt();
        modifiedAt = pet.getModifiedAt();
        if (pet.getPetUpload() != null) {
            profileImage = pet.getPetUpload().getFileName();
        }
    }
}
