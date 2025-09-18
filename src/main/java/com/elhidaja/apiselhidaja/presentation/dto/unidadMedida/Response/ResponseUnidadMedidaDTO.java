package com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUnidadMedidaDTO  {

    private Long id_unidad_medida;
    private String nombre;
    private Boolean status;
}
