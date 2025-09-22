package com.elhidaja.apiselhidaja.presentation.dto.subCategoria.Resquest;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestSubCategoriaIdDTO {


    @NotNull(message = "El id Sub Categoria es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor o igual a 1")
    private Long id;

}
