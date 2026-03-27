/**
 * US03: BÚSQUEDA DE PRODUCTO POR ID
 * Criterio de Aceptación: Si ID no existe o producto está eliminado → ResourceNotFoundException (404)
 * Implementación: Busca por ID, valida que exista y estaEliminado=false, retorna ProductoResponseDTO
 */
package com.programacion4.unidad3ej3.feature.producto.services.impl.domain;

import org.springframework.stereotype.Service;
import java.util.Optional;

import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoFindByIdService;
import com.programacion4.unidad3ej3.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej3.feature.producto.models.Producto;
import com.programacion4.unidad3ej3.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej3.feature.producto.mappers.ProductoMapper;
import com.programacion4.unidad3ej3.config.exceptions.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductoFindByIdService implements IProductoFindByIdService {

    private final IProductoRepository productoRepository;

    @Override
    public ProductoResponseDto findById(Long id) {
        // Busca el producto por ID
        Optional<Producto> productoOpt = productoRepository.findById(id);
        // Si no existe o está eliminado, lanza excepción 404
        if (productoOpt.isEmpty() || productoOpt.get().isEstaEliminado()) {
            throw new ResourceNotFoundException("Producto no encontrado");
        }
        // Mapea a DTO de respuesta
        return ProductoMapper.toResponseDto(productoOpt.get());
    }
}