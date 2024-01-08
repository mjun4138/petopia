package com.miraclerun.petopia.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ErrorResponse {

    private final String code;
    private final String message;
    private final Map<String, String> validation;

    @Builder
    public ErrorResponse(String code, String message, Map<String, String> validation) {
        this.code = code;
        this.message = message;
        this.validation = (validation != null) ? validation : new HashMap<>();
//        if (validation != null) {
//            this.validation = validation;
//        } else {
//            this.validation = new HashMap<>();
//        }
    }

    public void addValidation(String filedName, String errorMessage) {
        this.validation.put(filedName, errorMessage);
    }
}
