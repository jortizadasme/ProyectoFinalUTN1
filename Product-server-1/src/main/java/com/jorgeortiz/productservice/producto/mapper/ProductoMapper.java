package com.jorgeortiz.productservice.producto.mapper;

import com.jorgeortiz.productservice.producto.Producto;
import com.jorgeortiz.productservice.producto.dto.ProductoRequestDTO;
import com.jorgeortiz.productservice.producto.dto.ProductoResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public Producto toEntity(ProductoRequestDTO dto) {
        Producto producto = new Producto();
        producto.setClienteId(dto.clienteId());
        producto.setTipo(dto.tipo());
        producto.setNombre(dto.nombre());
        producto.setDescripcion(dto.descripcion());
        producto.setMontoAsociado(dto.montoAsociado());
        producto.setTasaInteres(dto.tasaInteres());
        producto.setActivo(dto.activo());
        producto.setFechaInicio(dto.fechaInicio());
        producto.setFechaVencimiento(dto.fechaVencimiento());
        return producto;
    }

    public ProductoResponseDTO toResponse(Producto producto) {
        return new ProductoResponseDTO(
                producto.getId(),
                producto.getClienteId(),
                producto.getTipo(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getMontoAsociado(),
                producto.getTasaInteres(),
                producto.isActivo(),
                producto.getFechaInicio(),
                producto.getFechaVencimiento(),
                producto.getFechaAlta()
        );
    }
}
