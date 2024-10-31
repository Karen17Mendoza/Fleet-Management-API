package com.example.Fleet.Management.API.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleSecurityException(Exception exception) {
        exception.printStackTrace();

        Map<String, String> errorResponse = new HashMap<>();
        HttpStatus status = HttpStatus.UNAUTHORIZED; // Default to 401

        if (exception instanceof BadCredentialsException) {
            status = HttpStatus.NOT_FOUND;  // 404
            errorResponse.put("error", "The username or password is incorrect");
        } else if (exception instanceof AccountStatusException) {
            status = HttpStatus.FORBIDDEN;  // 403
            errorResponse.put("error", "The account is locked");
        } else if (exception instanceof AccessDeniedException) {
            status = HttpStatus.FORBIDDEN;  // 403
            errorResponse.put("error", "You are not authorized to access this resource");
        } else if (exception instanceof SignatureException) {
            status = HttpStatus.FORBIDDEN;  // 403
            errorResponse.put("error", "The JWT signature is invalid");
        } else if (exception instanceof ExpiredJwtException) {
            status = HttpStatus.FORBIDDEN;  // 403
            errorResponse.put("error", "The JWT token has expired");
        } else {
            errorResponse.put("error", "Unknown internal server error.");
        }

        // Devolver una ResponseEntity con el mapa de error y el estado HTTP correspondiente
        return new ResponseEntity<>(errorResponse, status);
    }

    // Manejo específico de credenciales faltantes o inválidas
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException exception) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", exception.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);  // 400git
    }
}
