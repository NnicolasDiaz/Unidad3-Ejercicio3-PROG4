/**
 * US06: BAJA DE PRODUCTOS
 * Criterio de Aceptación: Soft delete (estaEliminado=true), si no existe → ResourceNotFoundException (404)
 * Implementación: Busca producto, valida existencia, marca como eliminado y guarda
 */
package com.programacion4.unidad3ej3.feature.producto.services.impl.domain;

import org.springframework.stereotype.Service;
import java.util.Optional;

import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoDeleteService;
import com.programacion4.unidad3ej3.feature.producto.models.Producto;
import com.programacion4.unidad3ej3.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej3.config.exceptions.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductoDeleteService implements IProductoDeleteService {

    private final IProductoRepository productoRepository;

    @Override
    public void deleteById(Long id) {
        // Busca el producto por ID
        Optional<Producto> productoOpt = productoRepository.findById(id);
        // Si no existe o ya está eliminado, lanza excepción 404
        if (productoOpt.isEmpty() || productoOpt.get().isEstaEliminado()) {
            throw new ResourceNotFoundException("Producto no encontrado");
        }
        // Realiza soft delete: marca como eliminado
        Producto producto = productoOpt.get();
        producto.setEstaEliminado(true);
        productoRepository.save(producto); // Guarda el cambio
    }
}