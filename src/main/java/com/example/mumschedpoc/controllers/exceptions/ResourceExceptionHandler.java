package com.example.mumschedpoc.controllers.exceptions;

import com.example.mumschedpoc.services.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException ex, HttpServletRequest request)
    {
        String errorMsg = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError(Instant.now(), status.value(), errorMsg, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> databaseError(DatabaseException ex, HttpServletRequest request)
    {
        String errorMsg = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError(Instant.now(), status.value(), errorMsg, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<StandardError> invalidEmail(InvalidEmailException ex, HttpServletRequest request)
    {
        String errorMsg = "User not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError(Instant.now(), status.value(), errorMsg, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<StandardError> wrongPassword(InvalidPasswordException ex, HttpServletRequest request)
    {
        String errorMsg = "Wrong password";
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        StandardError error = new StandardError(Instant.now(), status.value(), errorMsg, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<StandardError> duplicateResource(DuplicateResourceException ex, HttpServletRequest request)
    {
        String errorMsg = "Duplicate resource";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError error = new StandardError(Instant.now(), status.value(), errorMsg, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgument(IllegalArgumentException ex, HttpServletRequest request)
    {
        String errorMsg = ex.getMessage();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError(Instant.now(), status.value(), errorMsg, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }
}
