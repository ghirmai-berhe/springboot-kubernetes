package com.ghirmai.bookmarker_api.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@ControllerAdvice
public class GlobalExeptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handlesBadRequestException(BadRequestException ex, WebRequest request){
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), Instant.now(),request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
