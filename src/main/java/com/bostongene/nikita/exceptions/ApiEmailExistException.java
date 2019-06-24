package com.bostongene.nikita.exceptions;


public class ApiEmailExistException extends ApiException {
    public ApiEmailExistException(String message) {
        super(ApiExceptionType.EMAIL_EXIST, message);
    }
}
