/**
 * US03: BÚSQUEDA DE PRODUCTO POR ID
 * Criterio de Aceptación: Si ID no existe lanzar ResourceNotFoundException, retorna ProductoResponseDTO, status 200 OK
 */
package com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain;

import com.programacion4.unidad3ej3.feature.producto.dtos.response.ProductoResponseDto;

public interface IProductoFindByIdService {
    ProductoResponseDto findById(Long id);
}