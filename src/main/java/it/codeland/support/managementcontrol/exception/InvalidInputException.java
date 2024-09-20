package com.example.demo.exception;

public class InvalidInputException extends AbstractGraphQLException {
    public InvalidInputException(String message) {
        super(message);
    }
}
