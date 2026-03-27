/**
 * US03: BÚSQUEDA DE PRODUCTO POR ID
 * US06: BAJA DE PRODUCTOS
 * Criterio: Si el ID no existe, lanzar ResourceNotFoundException (HTTP 404)
 */
package com.programacion4.unidad3ej3.config.exceptions;

import java.util.List;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends CustomException {

    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND, List.of(message));
    }

    public ResourceNotFoundException(String message, List<String> errors) {
        super(message, HttpStatus.NOT_FOUND, errors);
    }
}