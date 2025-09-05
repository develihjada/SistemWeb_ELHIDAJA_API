package com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.tipoDocumentoD;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class TipoDocumentoIdentidadUpdateDTO {

    @NotBlank(message = "El código no puede estar vacío")
    @Size(max = 10, message = "El código debe tener máximo 10 caracteres")
    private String codigo;

    @NotBlank(message = "la validacion no puede estar vacía")
    private String validacionRegex;

    @Min(value = 1, message = "La longitud mínima debe ser mayor que 0")
    private Integer longitudMinima;

    @Min(value = 1, message = "La longitud máxima debe ser mayor que 0")
    private Integer longitudMaxima;

    @NotBlank(message = "El mensaje de error no puede estar vacío")
    private String mensajeError;

}
