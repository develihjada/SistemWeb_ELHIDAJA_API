package com.elhidaja.apiselhidaja.presentation.dto.ubicacion.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUbicacionInsertDTO {
    
    @NotNull(message = "El idAlmacen es obligatorio")
    @Min(value = 1, message = "El idAlmacen debe ser mayor a 0")
    private Long idAlmacen;

    @NotNull(message = "El idEstante es obligatorio")
    @Min(value = 1, message = "El idEstante debe ser mayor a 0")
    private Long idEstante;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 255, message = "La descripción no debe superar los 255 caracteres")
    private String descripcion;
}
