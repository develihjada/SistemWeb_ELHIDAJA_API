package com.elhidaja.apiselhidaja.presentation.dto.subCategoria.Resquest;

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
public class RequestSubCategoriaInsertDTO {

    @NotNull(message = "El id de la categoría es obligatorio")
    @Min(value = 1, message = "El id de la categoría debe ser mayor o igual a 1")
    private Long id_categoria;

    @NotBlank(message = "El nombre de la subcategoría no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre debe contener solo letras y espacios")
    private String nombre;
}
