package com.example.mumschedpoc.services.exceptions;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(Object id) {
        super("Duplicate resource. Id " + id);
    }
}
