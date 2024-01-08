package com.miraclerun.petopia.exception;

public class MemberNotFound extends PetopiaException {
    private static final String MESSAGE = "존재하지 않는 회원입니다.";

    public MemberNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
