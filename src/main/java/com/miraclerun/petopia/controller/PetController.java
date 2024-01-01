package com.miraclerun.petopia.controller;

import com.miraclerun.petopia.domain.Pet;
import com.miraclerun.petopia.dto.PetDto;
import com.miraclerun.petopia.request.CreatePetRequest;
import com.miraclerun.petopia.response.CreatePetResponse;
import com.miraclerun.petopia.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PetController {
    private final PetService petService;

    /**
     * 펫 등록
     */
    @PostMapping(value = "/pets", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public CreatePetResponse createPet(
            @RequestPart(name = "request") CreatePetRequest request,
            @RequestPart(name = "file", required = false) MultipartFile file
    ) throws IOException {
        Long petId = petService.createPet(request, file);

        return CreatePetResponse.builder()
                .id(petId)
                .build();
    }

    /**
     * 펫 삭제
     */
    @DeleteMapping("/pets/{petId}")
    public void deletePet(
            @PathVariable Long petId
    ) {
        petService.deletePet(petId);
    }

    /**
     * 회원별 펫 조회
     */
    @GetMapping("/pets/members/{memberId}")
    public List<PetDto> petsByMember(
            @PathVariable Long memberId
    ) {
        List<Pet> pets = petService.petsByMember(memberId);
        return pets.stream()
                .map(PetDto::new)
                .toList();
    }

    /**
     * 펫 이미지 등록
     */
    @PostMapping("/pets/{petId}/pet-uploads")
    public void setImage(
            @PathVariable Long petId,
            @RequestPart MultipartFile file
    ) throws IOException {
        petService.setImage(petId, file);
    }

//    /**
//     * 펫 이미지 삭제
//     */
//    @DeleteMapping("/pets/{petId}/uploads")
//    public void delImage(
//            @PathVariable Long petId
//    ) {
//        petService.delImage(petId);
//    }
}
