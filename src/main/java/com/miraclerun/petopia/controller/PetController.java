package com.miraclerun.petopia.controller;

import com.miraclerun.petopia.repository.PetRepository;
import com.miraclerun.petopia.request.CreatePetRequest;
import com.miraclerun.petopia.response.CreatePetResponse;
import com.miraclerun.petopia.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
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

}
