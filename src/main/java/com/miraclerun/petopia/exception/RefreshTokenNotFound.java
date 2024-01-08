package com.miraclerun.petopia.exception;

public class RefreshTokenNotFound extends PetopiaException {
    private static final String MESSAGE = "존재하지 않는 토큰입니다.";

    public RefreshTokenNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
