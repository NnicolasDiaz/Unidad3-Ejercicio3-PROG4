/**
 * US06: BAJA DE PRODUCTOS
 * Criterio de Aceptación: Soft delete (estaEliminado=true), valida existencia del producto, status 204 No Content
 */
package com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain;

public interface IProductoDeleteService {
    void deleteById(Long id);
}