package com.carpark.api.demo.exceptions;

public class InvalidCreditsException extends RuntimeException{

    public InvalidCreditsException(String message) {
        super(message);
    }
}
