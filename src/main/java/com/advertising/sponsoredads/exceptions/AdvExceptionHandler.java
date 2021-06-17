package com.advertising.sponsoredads.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AdvExceptionHandler {
    //   all exceptions
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorDto exceptionHandler(Exception ex) {
        return ErrorDto.builder()
            .date(LocalDateTime.now())
            .error(ex.getMessage())
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .build();
    }

    //    validExceptions
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDto handleValidationException(MethodArgumentNotValidException e, WebRequest request) {
        Map<String, String> map = new HashMap<>();
        BindingResult result = e.getBindingResult();
        result.getAllErrors().forEach(error -> {
            String field = error instanceof FieldError ? ((FieldError) error).getField() : error.getObjectName();
            String message = error.getDefaultMessage();
            map.put(field, message);
        });

        return ErrorDto.builder()
            .date(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .error(map.toString())
            .build();
    }

    //    not unique object
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(InvalidCampaignNameException.class)
    public ErrorDto handleNotUniqueCampaignNameException(InvalidCampaignNameException ex) {
        return ErrorDto.builder()
            .date(LocalDateTime.now())
            .error(ex.getMessage())
            .status(HttpStatus.CONFLICT.value())
            .build();
    }

    //    not unique object
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CampaignNotFoundException.class)
    public ErrorDto handleCampaignNotFoundException(CampaignNotFoundException ex) {
        return ErrorDto.builder()
            .date(LocalDateTime.now())
            .error(ex.getMessage())
            .status(HttpStatus.NOT_FOUND.value())
            .build();
    }
}
