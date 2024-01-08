package com.miraclerun.petopia.exception;

public class PetNotFound extends PetopiaException {
    private static final String MESSAGE = "존재하지 않는 펫입니다.";

    public PetNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
