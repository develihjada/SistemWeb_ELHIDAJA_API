package com.elhidaja.apiselhidaja.presentation.dto.producto.Request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RequestProductoIdDTO {
    @NotNull(message = "El ID del producto es obligatorio")
    @Min(value = 0, message = "El ID del producto debe ser mayor que cero")
    private Long id;
}
