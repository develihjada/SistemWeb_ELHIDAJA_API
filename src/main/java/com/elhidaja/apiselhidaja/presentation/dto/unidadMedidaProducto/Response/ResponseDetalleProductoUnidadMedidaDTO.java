package com.elhidaja.apiselhidaja.presentation.dto.unidadMedidaProducto.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "ProductoUnidadMedida", "exito", "mensaje",  "codigo" })
public class ResponseDetalleProductoUnidadMedidaDTO  extends GlobalResponse{
     private ResponseProductoUnidadMedidaDTO ProductoUnidadMedida;
}
