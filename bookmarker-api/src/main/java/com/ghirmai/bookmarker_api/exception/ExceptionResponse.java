package com.ghirmai.bookmarker_api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
@Data
@AllArgsConstructor
public class ExceptionResponse {
    private String message;
    private Instant timeStamp;
    private String details;

}
