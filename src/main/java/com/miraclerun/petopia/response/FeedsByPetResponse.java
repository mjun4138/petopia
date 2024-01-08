package com.miraclerun.petopia.response;

import com.miraclerun.petopia.dto.FeedDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class FeedsByPetResponse {
    private int feedCount;
    private List<FeedDto> feeds;

    public FeedsByPetResponse() {
    }

    @Builder
    public FeedsByPetResponse(int feedCount, List<FeedDto> feeds) {
        this.feedCount = feedCount;
        this.feeds = feeds;
    }
}
