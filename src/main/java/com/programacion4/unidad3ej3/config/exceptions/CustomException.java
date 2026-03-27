package com.programacion4.unidad3ej3.config.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;

import lombok.Getter;

// Clase base para excepciones personalizadas con status HTTP y lista de errores
@Getter
public abstract class CustomException extends RuntimeException {
    
    private final HttpStatus status;
    private final List<String> errors;

    public CustomException(String message, HttpStatus status, List<String> errors) {
        super(message);
        this.status = status; // Status HTTP correcto (antes estaba fijo en BAD_REQUEST)
        this.errors = errors;
    }

}
