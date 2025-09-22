package com.elhidaja.apiselhidaja.presentation.dto.almacen.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "almacen", "exito", "mensaje",  "codigo" })
public class ResponseDetalleAlmacenDTO extends GlobalResponse {
    private ResponseAlmacenDTO almacen;
}
