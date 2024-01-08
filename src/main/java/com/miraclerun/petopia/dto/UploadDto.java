package com.miraclerun.petopia.dto;

import com.miraclerun.petopia.domain.Upload;
import lombok.Getter;

@Getter
public class UploadDto {

    private Long id;
    private String fileName;

    public UploadDto() {
    }

    public UploadDto(Upload upload) {
        id = upload.getId();
        fileName = upload.getFileName();
    }
}
