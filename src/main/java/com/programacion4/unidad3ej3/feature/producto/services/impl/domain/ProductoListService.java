/**
 * US02: CONSULTA DE LISTADO COMPLETO
 * Criterio de Aceptación: Retorna lista ProductoResponseDTO, filtra productos no eliminados
 * Implementación: Obtiene todos los productos y filtra aquellos con estaEliminado=false
 */
package com.programacion4.unidad3ej3.feature.producto.services.impl.domain;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoListService;
import com.programacion4.unidad3ej3.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej3.feature.producto.models.Producto;
import com.programacion4.unidad3ej3.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej3.feature.producto.mappers.ProductoMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductoListService implements IProductoListService {

    private final IProductoRepository productoRepository;

    @Override
    public List<ProductoResponseDto> findAll() {
        // Obtiene todos los productos y filtra los no eliminados
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .filter(p -> !p.isEstaEliminado()) // Solo productos activos
                .map(ProductoMapper::toResponseDto) // Mapea a DTO de respuesta
                .collect(Collectors.toList());
    }
}