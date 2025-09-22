package com.elhidaja.apiselhidaja.presentation.dto.almacen.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAlmacenIdDTO {
    
    @NotNull(message = "El idAlmacen es obligatorio")
    @Min(value = 1, message = "El id del almacén debe ser mayor o igual a 1")
    private Long id;
}
