/**
 * US01, US02, US03, US06: CONTROLADOR CONSOLIDADO DE PRODUCTOS
 * 
 * US01 - REGISTRO DE NUEVOS PRODUCTOS
 * POST /productos → Retorna status 201 Created con ProductoResponseDTO
 * 
 * US02 - CONSULTA DE LISTADO COMPLETO
 * GET /productos → Retorna lista ProductoResponseDTO, status 200 OK
 * 
 * US03 - BÚSQUEDA DE PRODUCTO POR ID
 * GET /productos/{id} → Retorna ProductoResponseDTO o 404 Not Found
 * 
 * US06 - BAJA DE PRODUCTOS
 * DELETE /productos/{id} → Soft delete, status 204 No Content
 */
package com.programacion4.unidad3ej3.feature.producto.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import jakarta.validation.Valid;

import com.programacion4.unidad3ej3.config.BaseResponse;
import com.programacion4.unidad3ej3.feature.producto.dtos.request.ProductoCreateRequestDto;
import com.programacion4.unidad3ej3.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoCreateService;
import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoListService;
import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoFindByIdService;
import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoDeleteService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/productos") // Ruta base para productos
@AllArgsConstructor
public class ProductoController {

    private final IProductoCreateService productoCreateService;
    private final IProductoListService productoListService;
    private final IProductoFindByIdService productoFindByIdService;
    private final IProductoDeleteService productoDeleteService;

    // POST /productos - US01: Crea un nuevo producto
    @PostMapping
    public ResponseEntity<BaseResponse<ProductoResponseDto>> create(
        @Valid @RequestBody ProductoCreateRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(
                BaseResponse.ok(
                    productoCreateService.create(dto), 
                    "Producto creado correctamente"
                )
            );
    }

    // GET /productos - US02: Lista todos los productos no eliminados
    @GetMapping
    public ResponseEntity<BaseResponse<List<ProductoResponseDto>>> findAll() {
        return ResponseEntity.ok(
            BaseResponse.ok(
                productoListService.findAll(), 
                "Productos obtenidos correctamente"
            )
        );
    }

    // GET /productos/{id} - US03: Busca producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductoResponseDto>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
            BaseResponse.ok(
                productoFindByIdService.findById(id), 
                "Producto obtenido correctamente"
            )
        );
    }

    // DELETE /productos/{id} - US06: Elimina producto (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        productoDeleteService.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}