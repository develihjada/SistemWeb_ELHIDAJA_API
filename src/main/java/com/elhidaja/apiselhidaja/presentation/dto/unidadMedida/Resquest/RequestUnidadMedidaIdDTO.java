package com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Resquest;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestUnidadMedidaIdDTO  {
    
    @NotNull(message = "El idUnidadMedida es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor o igual a 1")
    private Long id_unidad_medida;

}
