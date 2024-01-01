package com.miraclerun.petopia.repository;

import com.miraclerun.petopia.domain.Pet;
import com.miraclerun.petopia.domain.Upload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UploadRepository extends JpaRepository<Upload, Long>, UploadRepositoryCustom{

}
