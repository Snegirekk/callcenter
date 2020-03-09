package com.snegirekk.callcenter.controller;

import com.snegirekk.callcenter.dto.ErrorDto;
import com.snegirekk.callcenter.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@RequestMapping(path = "/api/v1")
public class V1ApiController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDto> handleRouteNotFound(NoHandlerFoundException exception) {

        String message = String.format("Route '%s %s' not found.", exception.getHttpMethod(), exception.getRequestURL());
        ErrorDto error = new ErrorDto(message, HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDto> handleNotReadableMessage(HttpMessageNotReadableException exception) {

        ErrorDto error = new ErrorDto(exception.getMessage(), HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorDto> handleApiException(ApiException exception) {

        ErrorDto error = new ErrorDto(exception.getMessage(), exception.getHttpStatus().value());
        return new ResponseEntity<>(error, exception.getHttpStatus());
    }
}
