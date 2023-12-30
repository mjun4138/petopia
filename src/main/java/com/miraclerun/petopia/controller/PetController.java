package com.miraclerun.petopia.controller;

import com.miraclerun.petopia.repository.PetRepository;
import com.miraclerun.petopia.request.CreatePetRequest;
import com.miraclerun.petopia.response.CreatePetResponse;
import com.miraclerun.petopia.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PetController {
    private final PetService petService;

    /**
     * 펫 등록
     */
    @PostMapping("/pets")
    public CreatePetResponse createPet(@RequestBody CreatePetRequest request) {
        Long petId = petService.createPet(request);

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
}
