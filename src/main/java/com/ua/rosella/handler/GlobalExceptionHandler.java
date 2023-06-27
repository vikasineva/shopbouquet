package com.ua.rosella.handler;

import com.ua.rosella.exceptions.ObjectNotValidException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ObjectNotValidException.class)
    public ResponseEntity<?> handleValidationException(ObjectNotValidException exception) {
        return ResponseEntity.badRequest().body(exception.getErrorMessages());
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException exception) {
        return ResponseEntity.badRequest().body(List.of("Невірний пароль"));
    }
}
