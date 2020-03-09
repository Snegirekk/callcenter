package com.snegirekk.callcenter.dto;

public class ErrorDto {

    public int statusCode;
    public String message;

    public ErrorDto(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
