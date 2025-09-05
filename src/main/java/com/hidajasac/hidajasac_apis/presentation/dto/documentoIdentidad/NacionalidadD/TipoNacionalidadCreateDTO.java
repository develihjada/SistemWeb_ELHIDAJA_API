package com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoNacionalidadCreateDTO {

    @NotBlank(message = "El nombre del tipo de nacionalidad no puede estar vacío")
    @Size(min = 3, max = 20, message = "El nombre del ipo de nacionalidad  debe tener entre 3 y 20 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre del tipo de nacionalidad  solo puede contener letras y espacios")
    private String tipoNacionalidad;
}
