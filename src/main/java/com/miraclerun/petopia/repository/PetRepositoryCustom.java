package com.miraclerun.petopia.repository;

import com.miraclerun.petopia.domain.Pet;

import java.util.List;

public interface PetRepositoryCustom {
    List<Pet> petsByMember(Long memberId);
}
