package com.miraclerun.petopia.service;

import com.miraclerun.petopia.domain.Member;
import com.miraclerun.petopia.domain.Pet;
import com.miraclerun.petopia.repository.MemberRepository;
import com.miraclerun.petopia.repository.PetRepository;
import com.miraclerun.petopia.request.CreatePetRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PetService {

    private final MemberRepository memberRepository;
    private final PetRepository petRepository;

    /**
     * 펫 등록
     */
    @Transactional
    public Long createPet(CreatePetRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(RuntimeException::new);

        Pet pet = Pet.builder()
                .member(member)
                .name(request.getName())
                .intro(request.getIntro())
                .build();
        petRepository.save(pet);

        return pet.getId();
    }
}
