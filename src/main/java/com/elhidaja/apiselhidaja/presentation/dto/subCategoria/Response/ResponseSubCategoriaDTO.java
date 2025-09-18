package com.elhidaja.apiselhidaja.presentation.dto.subCategoria.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSubCategoriaDTO {

    private Long id_sub_categoria;
    private String categoria_nombre;
    private String sub_categoria_nombre;
    private Boolean status;
}
