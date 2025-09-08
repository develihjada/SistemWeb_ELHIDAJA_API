package com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NacionalidadCreateDTO {

    @NotBlank(message = "El nombre de la nacionalidad no puede estar vacío")
    @Size(min = 3, max = 20, message = "El nombre de la  nacionalidad  debe tener entre 3 y 20 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre de la nacionalidad  solo puede contener letras y espacios")
    private String nombre;
}
