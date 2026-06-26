package org.example.migbacktobasics.dto;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record ApiErrorResponse(ZonedDateTime dateTime, int status, String message) {

    public ApiErrorResponse(HttpStatus status, Exception e) {
        this(ZonedDateTime.now(), status.value(), e.getMessage());
    }

}
