package com.elhidaja.apiselhidaja.presentation.dto.categoria.Resquest;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequesteCategoriaIdDTO {

    @NotNull(message = "El idCategoria es obligatorio")
    @Min(value = 1, message = "El id de la categoría debe ser mayor o igual a 1")
    private Long id_categoria;

}
