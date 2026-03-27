/**
 * US02: CONSULTA DE LISTADO COMPLETO
 * Criterio de Aceptación: Retorna lista ProductoResponseDTO, status 200 OK con lista vacía si no hay
 */
package com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain;

import java.util.List;
import com.programacion4.unidad3ej3.feature.producto.dtos.response.ProductoResponseDto;

public interface IProductoListService {
    List<ProductoResponseDto> findAll();
}