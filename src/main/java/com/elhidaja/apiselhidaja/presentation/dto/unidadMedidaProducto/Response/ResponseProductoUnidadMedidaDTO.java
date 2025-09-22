package com.elhidaja.apiselhidaja.presentation.dto.unidadMedidaProducto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductoUnidadMedidaDTO {
    private Long id;
    private String producto;
    private String unidadMedida;
    private Boolean status;
}
