package com.ghirmai.bookmarker_api.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String ex){
        super(ex);
    }
}
