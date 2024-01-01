package com.miraclerun.petopia.repository;

import com.miraclerun.petopia.domain.Pet;
import com.miraclerun.petopia.domain.PetUpload;
import com.miraclerun.petopia.domain.Upload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetUploadRepository extends JpaRepository<PetUpload, Long>, PetUploadRepositoryCustom{

    void deleteByPet(Pet pet);
}
