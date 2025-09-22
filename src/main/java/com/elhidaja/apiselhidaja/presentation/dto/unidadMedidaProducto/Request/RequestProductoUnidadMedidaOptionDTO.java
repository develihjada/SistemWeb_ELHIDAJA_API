package com.elhidaja.apiselhidaja.presentation.dto.unidadMedidaProducto.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

import jakarta.validation.constraints.NotNull;
import lombok.*;
@Data
public class RequestProductoUnidadMedidaOptionDTO {
    
    @NotNull(message = "La opción es obligatoria")
    @ValidOption  
    private Long option;
}
