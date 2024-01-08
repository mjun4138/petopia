package com.miraclerun.petopia.exception;

public class InvalidPassword extends PetopiaException {
    private static final String MESSAGE = "잘못된 요청입니다.";

    public InvalidPassword() {
        super(MESSAGE);
    }

    public InvalidPassword(String fieldName, String message) {
        super(MESSAGE);
        addValidation(fieldName, message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
