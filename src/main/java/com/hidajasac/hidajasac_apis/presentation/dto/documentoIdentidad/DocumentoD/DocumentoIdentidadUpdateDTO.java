package com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.DocumentoD;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class DocumentoIdentidadUpdateDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 10, message = "El nombre debe tener máximo 10 caracteres")
    private String nombre;

    @NotBlank(message = "La descripcion no puede estar vacía")
    @Size(min = 10, message = "la descripcion debe tener minimo 10 caracteres")
    private String descripcion;

    @NotBlank(message = "El mensaje de error no puede estar vacío")
    private String mensajeError;

}
