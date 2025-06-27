package com.kelsyfrank.learning.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    // Make sure a bad requet 400 is sent on MethodArgumentNotValid exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        System.out.println("Validation triggered: " + ex.getMessage());
        return ResponseEntity.badRequest().body("Validation failed: " + ex.getMessage());
    }
}
