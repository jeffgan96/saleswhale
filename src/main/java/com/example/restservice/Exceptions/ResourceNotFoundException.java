package com.example.restservice.Exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String s) {
        super(String.format("Could not find resource: %s", s));
    }
}
