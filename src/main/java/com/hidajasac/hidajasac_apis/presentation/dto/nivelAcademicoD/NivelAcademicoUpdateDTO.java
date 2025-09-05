package com.hidajasac.hidajasac_apis.presentation.dto.nivelAcademicoD;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NivelAcademicoUpdateDTO {

    @NotBlank(message = "El  nombre del tipo de nivel academico no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre del tipo de nivel academico debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre del tipo de nivel academico solo puede contener letras y espacios")
    private String tipoNivel;
}
