package com.bostongene.nikita.exceptions;

public class ApiUserNotFoundException extends ApiException {
    public ApiUserNotFoundException(String message) {
        super(ApiExceptionType.USER_NOT_FOUND, message);
    }
}

