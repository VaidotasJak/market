package com.skydive.market.exceptions;

public class RegistrationAlreadyExistsException extends RuntimeException{
    public RegistrationAlreadyExistsException(String message){
        super(message);
    }
}
