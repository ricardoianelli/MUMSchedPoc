package com.example.mumschedpoc.services.exceptions;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String email) {
        super("No user found with email " + email);
    }
}
