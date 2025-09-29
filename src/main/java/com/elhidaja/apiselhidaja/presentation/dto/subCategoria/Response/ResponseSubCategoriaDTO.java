package com.elhidaja.apiselhidaja.presentation.dto.subCategoria.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSubCategoriaDTO {

    private Long id;
    private String categoria;
    private String nombre;
    private Boolean status;
}
