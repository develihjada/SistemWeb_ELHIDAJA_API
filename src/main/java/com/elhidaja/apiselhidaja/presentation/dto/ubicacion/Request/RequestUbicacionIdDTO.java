package com.elhidaja.apiselhidaja.presentation.dto.ubicacion.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class RequestUbicacionIdDTO {
    @NotNull(message = "El idUbicacion es obligatorio")
    @Min(value = 1, message = "El idUbicacion debe ser mayor a 0")
    private Long id_ubicacion;
}
