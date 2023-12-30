package com.miraclerun.petopia.controller;

import com.miraclerun.petopia.domain.Feed;
import com.miraclerun.petopia.request.CreateFeedRequest;
import com.miraclerun.petopia.response.CreateFeedResponse;
import com.miraclerun.petopia.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FeedController {
    private final FeedService feedService;

    /**
     * 피드 등록
     */
    @PostMapping(value = "/feeds", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public CreateFeedResponse createFeed(
            @RequestPart(name = "request") CreateFeedRequest request,
            @RequestPart(name = "files") List<MultipartFile> files
    ) throws IOException {
        Long feedId = feedService.createFeed(request, files);

        return CreateFeedResponse.builder()
                .id(feedId)
                .build();
    }
}
