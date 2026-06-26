package org.example.migbacktobasics.exception;

import org.example.migbacktobasics.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleMathException(ArithmeticException e) {
        return new ApiErrorResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ApiErrorResponse handleArrayException(IndexOutOfBoundsException e) {
        return new ApiErrorResponse(HttpStatus.BAD_GATEWAY, e);
    }

}
