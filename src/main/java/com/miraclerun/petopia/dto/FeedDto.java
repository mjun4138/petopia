package com.miraclerun.petopia.dto;

import com.miraclerun.petopia.domain.Feed;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FeedDto {

    private Long id;
    private Long petId;
    private String content;
    private int heartCount;
    private List<UploadDto> uploads;

    public FeedDto() {
    }

    public FeedDto(Feed feed) {
        id = feed.getId();
        petId = feed.getPet().getId();
        content = feed.getContent();
        heartCount = feed.getHeartCount();
        uploads = feed.getUploads().stream()
                .map(UploadDto::new)
                .collect(Collectors.toList());
    }
}
