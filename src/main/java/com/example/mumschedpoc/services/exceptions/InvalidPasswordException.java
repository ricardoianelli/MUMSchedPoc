package com.example.mumschedpoc.services.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String email) {
        super("Invalid password for user with email " + email);
    }
}
