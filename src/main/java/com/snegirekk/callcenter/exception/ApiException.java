package com.snegirekk.callcenter.exception;

import org.springframework.http.HttpStatus;

abstract public class ApiException extends Exception {

    protected HttpStatus httpStatus;

    public ApiException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
