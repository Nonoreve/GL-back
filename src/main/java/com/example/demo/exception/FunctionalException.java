package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public abstract class FunctionalException extends Exception {
    private final HttpStatus status;

    public FunctionalException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}