package com.example.Fleet.Management.API.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponseHandler {

    public static ResponseEntity<Object> generateErrorResponse(String errorMessage, HttpStatus status) {
        return new ResponseEntity<>(new ErrorResponse(errorMessage), status);
    }

    // Clase interna para estructurar el mensaje de error
    public static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}
