package com.elhidaja.apiselhidaja.presentation.dto.estante.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestEstanteIdDTO {
    
    @NotNull(message = "El id_estante es obligatorio")
    @Min(value = 1, message = "El id del estante debe ser mayor o igual a 1")
    private Long id;
}
