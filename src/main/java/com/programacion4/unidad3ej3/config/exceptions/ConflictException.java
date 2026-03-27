/**
 * US01: REGISTRO DE NUEVOS PRODUCTOS
 * Criterio de Aceptación: Si el nombre del producto ya existe, lanzar excepción Conflicto (HTTP 409)
 */
package com.programacion4.unidad3ej3.config.exceptions;

import java.util.List;
import org.springframework.http.HttpStatus;

public class ConflictException extends CustomException {

    public ConflictException(String message) {
        super(message, HttpStatus.CONFLICT, List.of(message));
    }

    public ConflictException(String message, List<String> errors) {
        super(message, HttpStatus.CONFLICT, errors);
    }
}