package com.miraclerun.petopia.repository;

import com.miraclerun.petopia.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long>, PetRepositoryCustom {
}
