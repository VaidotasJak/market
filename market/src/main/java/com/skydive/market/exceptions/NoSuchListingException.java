package com.skydive.market.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoSuchListingException extends RuntimeException{
    public NoSuchListingException(String message){
        super(message);
    }
}
