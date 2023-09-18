package com.carpark.api.demo.exceptions;

public class InvalidCarTypeException extends RuntimeException{

    public InvalidCarTypeException(String message) {
        super(message);
    }
}
