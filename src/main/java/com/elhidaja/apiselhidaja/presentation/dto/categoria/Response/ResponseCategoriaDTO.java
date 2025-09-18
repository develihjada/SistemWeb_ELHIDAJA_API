package com.elhidaja.apiselhidaja.presentation.dto.categoria.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCategoriaDTO  {

    private Long id_categoria;
    private String nombre;
    private Boolean status;
}
