package com.miraclerun.petopia.exception;

public class UploadNotFound extends PetopiaException {
    private static final String MESSAGE = "존재하지 않는 업로드입니다.";

    public UploadNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
