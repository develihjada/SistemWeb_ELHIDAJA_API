package com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Resquest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUnidadMedidaInsertDTO {

    @NotBlank(message = "El nombre de la unidad de medida no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre debe contener solo letras y espacios")
    private String nombre;

}
