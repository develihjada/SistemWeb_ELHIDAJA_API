package com.elhidaja.apiselhidaja.presentation.dto.unidadMedidaProducto.Response;

import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

@JsonPropertyOrder({ "ProductoUnidadMedidas", "exito", "mensaje",  "codigo" })

public class ResponseProductoUnidadMedidaAllDTO extends GlobalResponse{
    private List<ResponseProductoUnidadMedidaDTO> ProductoUnidadMedidas;
}
