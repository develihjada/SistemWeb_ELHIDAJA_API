package com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUnidadMedidaDTO  {

    private Long id;
    private String nombre;
    private Boolean status;
}
