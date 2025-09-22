package com.elhidaja.apiselhidaja.presentation.dto.producto.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "producto", "exito", "mensaje", "codigo" })
public class ResponseDetalleProductoDTO extends GlobalResponse {
     private ResponseProductoDTO producto;
}
