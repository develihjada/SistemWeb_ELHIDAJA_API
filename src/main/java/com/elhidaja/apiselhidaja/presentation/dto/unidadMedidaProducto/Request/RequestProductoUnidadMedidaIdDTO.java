package com.elhidaja.apiselhidaja.presentation.dto.unidadMedidaProducto.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestProductoUnidadMedidaIdDTO {
    @NotNull(message = "El id del producto_unidad_medida es obligatorio")
    @Min(value = 1, message = "El id del producto_unidad_medida debe ser mayor o igual a 1")
    private Long id;

}
