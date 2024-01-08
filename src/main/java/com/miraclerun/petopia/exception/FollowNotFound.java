package com.miraclerun.petopia.exception;

public class FollowNotFound extends PetopiaException {
    private static final String MESSAGE = "존재하지 않는 팔로우입니다.";

    public FollowNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
