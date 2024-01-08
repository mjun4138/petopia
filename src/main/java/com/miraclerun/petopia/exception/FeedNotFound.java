package com.miraclerun.petopia.exception;

public class FeedNotFound extends PetopiaException {
    private static final String MESSAGE = "존재하지 않는 피드입니다.";

    public FeedNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
