package com.miraclerun.petopia.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "pet_upload")
public class PetUpload {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_upload_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private Long fileSize;

    public PetUpload() {
    }

    //==생성 메서드==//
    @Builder
    public PetUpload(Pet pet, String fileName, String filePath, Long fileSize) {
        setPet(pet);
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    //==연관관계 메서드=//
    public void setPet(Pet pet) {
        this.pet = pet;
        pet.setPetUpload(this);
    }
}
