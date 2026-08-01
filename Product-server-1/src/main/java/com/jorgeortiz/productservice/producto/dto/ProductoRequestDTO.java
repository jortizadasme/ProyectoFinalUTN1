package com.jorgeortiz.productservice.producto.dto;

import com.jorgeortiz.productservice.producto.TipoProducto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProductoRequestDTO(
        Long clienteId,
        TipoProducto tipo,
        String nombre,
        String descripcion,
        BigDecimal montoAsociado,
        BigDecimal tasaInteres,
        boolean activo,
        LocalDate fechaInicio,
        LocalDate fechaVencimiento
) {}
