package com.hidajasac.hidajasac_apis.presentation.dto.rolD;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolUpdateDTO {

    @NotBlank(message = "El nombre del tipo no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre del tipo debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre del  tipo solo puede contener letras y espacios")
    private String tipoRol;
}
