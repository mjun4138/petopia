package com.miraclerun.petopia.service;

import com.miraclerun.petopia.domain.Member;
import com.miraclerun.petopia.domain.Pet;
import com.miraclerun.petopia.domain.PetUpload;
import com.miraclerun.petopia.domain.Upload;
import com.miraclerun.petopia.exception.InvalidPassword;
import com.miraclerun.petopia.exception.MemberNotFound;
import com.miraclerun.petopia.exception.PetNotFound;
import com.miraclerun.petopia.repository.MemberRepository;
import com.miraclerun.petopia.repository.PetRepository;
import com.miraclerun.petopia.repository.PetUploadRepository;
import com.miraclerun.petopia.repository.UploadRepository;
import com.miraclerun.petopia.request.CreatePetRequest;
import com.miraclerun.petopia.request.DeletePetRequest;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PetService {

    private final MemberRepository memberRepository;
    private final PetRepository petRepository;
    private final PetUploadRepository petUploadRepository;
    private final PasswordEncoder encoder;
    @Value("${pet-upload.path}") private String uploadPath;

    /**
     * 펫 등록
     */
    @Transactional
    public Long createPet(CreatePetRequest request,  MultipartFile file) throws IOException {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(MemberNotFound::new);

        Pet pet = Pet.builder()
                .member(member)
                .name(request.getName())
                .intro(request.getIntro())
                .build();
        petRepository.save(pet);

        if (file != null) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File saveFile = new File(uploadPath, fileName);
            file.transferTo(saveFile);

            PetUpload upload = PetUpload.builder()
                    .pet(pet)
                    .fileName(fileName)
                    .filePath("/uploads/" + fileName)
                    .fileSize(file.getSize())
                    .build();
            petUploadRepository.save(upload);
        }



        return pet.getId();
    }

    /**
     * 펫 삭제
     */
    @Transactional
    public void deletePet(Long petId, DeletePetRequest request) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(PetNotFound::new);
        if (!encoder.matches(request.getPassword(), pet.getMember().getPassword())) {
            throw new InvalidPassword("password", "비밀번호가 일치하지 않습니다.");
        }

        petRepository.delete(pet);
    }

    /**
     * 펫 단건 조회
     */
    public Pet pet(Long id) {
        return petRepository.findById(id)
                .orElseThrow(PetNotFound::new);
    }

    /**
     * 회원별 펫 조회
     */
    public List<Pet> petsByMember(Long memberId) {
        return petRepository.petsByMember(memberId);
    }

    /**
     * 펫 이미지 등록
     */
    @Transactional
    public void setImage(Long petId, MultipartFile file) throws IOException {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(PetNotFound::new);

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File saveFile = new File(uploadPath, fileName);
        file.transferTo(saveFile);

        PetUpload upload = PetUpload.builder()
                .pet(pet)
                .fileName(fileName)
                .filePath("/uploads/" + fileName)
                .fileSize(file.getSize())
                .build();

        pet.setPetUpload(null);
        petRepository.flush();
        petUploadRepository.save(upload);
    }
}
