package com.jorgeortiz.productservice.producto.dto;

import com.jorgeortiz.productservice.producto.TipoProducto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProductoResponseDTO(
        Long id,
        Long clienteId,
        TipoProducto tipo,
        String nombre,
        String descripcion,
        BigDecimal montoAsociado,
        BigDecimal tasaInteres,
        boolean activo,
        LocalDate fechaInicio,
        LocalDate fechaVencimiento,
        LocalDateTime fechaAlta
) {}
