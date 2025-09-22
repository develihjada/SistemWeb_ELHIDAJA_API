package com.elhidaja.apiselhidaja.presentation.dto.producto.Response;

import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;
@Getter
@Setter

@JsonPropertyOrder({ "productos", "exito", "mensaje",  "codigo" })
public class ResponseProductoAllDTO extends GlobalResponse {
     private List<ResponseProductoDTO> productos;
}
