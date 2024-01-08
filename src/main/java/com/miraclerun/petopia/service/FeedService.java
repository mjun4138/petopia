package com.miraclerun.petopia.service;

import com.miraclerun.petopia.domain.Feed;
import com.miraclerun.petopia.domain.Pet;
import com.miraclerun.petopia.domain.Upload;
import com.miraclerun.petopia.repository.FeedRepository;
import com.miraclerun.petopia.repository.PetRepository;
import com.miraclerun.petopia.request.CreateFeedRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FeedService {
    private final PetRepository petRepository;
    private final FeedRepository feedRepository;
    @Value("${feed-upload.path}") private String uploadPath;

    /**
     * 피드 등록
     */
    public Long createFeed(CreateFeedRequest request, List<MultipartFile> files) throws IOException {
        Long petId = request.getPetId();
        Pet pet = petRepository.findById(petId)
                .orElseThrow(RuntimeException::new);

        List<Upload> uploads = new ArrayList<>();

        for (MultipartFile file : files) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File saveFile = new File(uploadPath, fileName);
            file.transferTo(saveFile);

            Upload upload = Upload.builder()
                    .fileName(fileName)
                    .filePath("/uploads/" + fileName)
                    .fileSize(file.getSize())
                    .build();

            uploads.add(upload);
        }

        Feed feed = Feed.builder()
                .pet(pet)
                .content(request.getContent())
                .uploads(uploads)
                .build();

        feedRepository.save(feed);

        return feed.getId();
    }

    /**
     * 펫별 피드 조회
     */
    public List<Feed> feedsByPet(Long petId) {
        return feedRepository.feedsByPet(petId);
    }

    /**
     * 펫별 피드 수 조회
     */
    public int feedsCountByPet(Long petId) {
        return feedRepository.feedsCountByPet(petId);
    }
}
