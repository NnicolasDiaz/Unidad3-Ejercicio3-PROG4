/**
 * US01: REGISTRO DE NUEVOS PRODUCTOS
 * Criterios de Aceptación:
 * - Capitalización automática: nombre y descripción (1era mayúscula, resto minúscula)
 * - Si el nombre ya existe → ConflictException (409)
 * - ID autogenerado y estaEliminado inicializado en false
 * - Retorna ProductoResponseDTO con objeto creado
 */
package com.programacion4.unidad3ej3.feature.producto.services.impl.domain;

import com.programacion4.unidad3ej3.config.exceptions.ConflictException;
import org.springframework.stereotype.Service;

import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoCreateService;
import com.programacion4.unidad3ej3.feature.producto.dtos.request.ProductoCreateRequestDto;
import com.programacion4.unidad3ej3.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej3.feature.producto.models.Producto;
import com.programacion4.unidad3ej3.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej3.feature.producto.mappers.ProductoMapper;
import com.programacion4.unidad3ej3.feature.producto.services.interfaces.commons.IProductoExistByNameService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ProductoCreateService implements IProductoCreateService {

    private final IProductoExistByNameService productoExistByNameService;

    private final IProductoRepository productoRepository;

    @Override
    public ProductoResponseDto create(ProductoCreateRequestDto dto) {

        // Normalizar nombre y descripcion a formato capitalizado
        dto.setNombre(capitalize(dto.getNombre()));
        dto.setDescripcion(capitalize(dto.getDescripcion()));

        // Verificar si el nombre ya existe (lanza ConflictException 409)
        if (productoExistByNameService.existByName(dto.getNombre())) {
            throw new ConflictException("El nombre del producto ya existe");
        }

        // Mapear DTO a entidad
        Producto productoAGuardar = ProductoMapper.toEntity(dto);
        productoAGuardar.setEstaEliminado(false); // Inicializar como no eliminado
        
        // Guardar en BD
        Producto productoGuardado = productoRepository.save(productoAGuardar);

        // Retornar DTO de respuesta
        return ProductoMapper.toResponseDto(productoGuardado);
    }

    // Método auxiliar para capitalizar texto (1ra letra mayúscula, resto minúscula)
    private String capitalize(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }
}
