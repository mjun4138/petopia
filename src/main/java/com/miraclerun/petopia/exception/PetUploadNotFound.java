package com.miraclerun.petopia.exception;

public class PetUploadNotFound extends PetopiaException {
    private static final String MESSAGE = "존재하지 않는 펫업로드입니다.";

    public PetUploadNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
