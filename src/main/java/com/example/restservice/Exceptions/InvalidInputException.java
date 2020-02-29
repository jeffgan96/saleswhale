package com.example.restservice.Exceptions;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String field, String value) {
        super(String.format("Invalid user input for %s: %s", field, value));
    }
}
