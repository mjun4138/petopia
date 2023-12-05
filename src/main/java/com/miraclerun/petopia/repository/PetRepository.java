package com.miraclerun.petopia.repository;

import com.miraclerun.petopia.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long>, PetRepositoryCustom {
}
