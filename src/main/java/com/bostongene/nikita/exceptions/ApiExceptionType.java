package com.bostongene.nikita.exceptions;

import org.springframework.http.HttpStatus;

public enum ApiExceptionType {
    EMAIL_EXIST,
    USER_NOT_FOUND;

    public HttpStatus getHttpStatus() {
        switch (this) {
            case USER_NOT_FOUND:
                return HttpStatus.NOT_FOUND;
            case EMAIL_EXIST:
                return HttpStatus.BAD_REQUEST;
            default:
                return HttpStatus.BAD_REQUEST;
        }
    }
}
