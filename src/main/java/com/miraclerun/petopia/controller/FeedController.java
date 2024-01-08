package com.miraclerun.petopia.controller;

import com.miraclerun.petopia.domain.Feed;
import com.miraclerun.petopia.dto.FeedDto;
import com.miraclerun.petopia.request.CreateFeedRequest;
import com.miraclerun.petopia.response.CreateFeedResponse;
import com.miraclerun.petopia.response.FeedsByPetResponse;
import com.miraclerun.petopia.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FeedController {
    private final FeedService feedService;

    /**
     * 피드 등록
     */
    @PostMapping(value = "/feeds", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public CreateFeedResponse createFeed(
            @RequestPart(name = "request") CreateFeedRequest request,
            @RequestPart(name = "files") List<MultipartFile> files
    ) throws IOException {
        Long feedId = feedService.createFeed(request, files);

        return CreateFeedResponse.builder()
                .id(feedId)
                .build();
    }

    /**
     * 펫별 피드 조회
     */
    @GetMapping("/feeds/pets/{petId}")
    public FeedsByPetResponse feedsByPet(
            @PathVariable(name = "petId") Long petId
    ) {
        List<Feed> feeds = feedService.feedsByPet(petId);
        int feedCount = feedService.feedsCountByPet(petId);

        List<FeedDto> result = feeds.stream()
                .map(FeedDto::new)
                .collect(Collectors.toList());

        return FeedsByPetResponse.builder()
                .feedCount(feedCount)
                .feeds(result)
                .build();
    }
}
