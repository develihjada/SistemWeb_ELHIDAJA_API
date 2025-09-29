package com.elhidaja.apiselhidaja.presentation.dto.pallet.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPalletInsertDTO {
    
    @NotBlank(message = "El código del pallet no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z0-9\\-\\s]+$", message = "El código del pallet solo puede contener letras, números, espacios y guiones")
    @Size(min = 1, max = 50, message = "El código del pallet debe tener máximo 50 caracteres")
    private String codigo;

    @Size(max = 255, message = "La descripción debe tener máximo 255 caracteres")
    private String descripcion;

    @NotNull(message = "El id del estante es obligatorio")
    @Min(value = 1, message = "El id del estante debe ser mayor o igual a 1")
    private Long idEstante;
}
