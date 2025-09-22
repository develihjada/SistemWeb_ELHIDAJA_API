package com.elhidaja.apiselhidaja.presentation.dto.almacen.Request;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAlmacenInsertDTO {
    
    @NotBlank(message = "El código no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z0-9\\-\\s]+$", message = "El código del almacen solo puede contener letras, números, espacios y guiones")
    @Size(min = 2, max = 50, message = "El código debe tener entre 2 y 50 caracteres")
    private String codigo;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(min = 3, max = 255, message = "La descripción debe tener entre 3 y 255 caracteres")
    private String descripcion;
}
