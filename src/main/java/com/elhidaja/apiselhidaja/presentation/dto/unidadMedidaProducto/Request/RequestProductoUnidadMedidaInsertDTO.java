package com.elhidaja.apiselhidaja.presentation.dto.unidadMedidaProducto.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestProductoUnidadMedidaInsertDTO {
    @NotNull(message = "El id del producto es obligatorio")
    @Min(value = 1, message = "El id del producto debe ser mayor o igual a 1")
    private Long id_producto;

    @NotNull(message = "El id de unidad de medida es obligatorio")
    @Min(value = 1, message = "El id de unidad de medida debe ser mayor o igual a 1")
    private Long id_unidad_medida;
}
