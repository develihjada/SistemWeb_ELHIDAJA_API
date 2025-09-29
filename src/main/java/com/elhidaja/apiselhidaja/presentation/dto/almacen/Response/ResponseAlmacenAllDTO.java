package com.elhidaja.apiselhidaja.presentation.dto.almacen.Response;

import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "almacenes", "exito", "mensaje",  "codigo" })
public class ResponseAlmacenAllDTO extends GlobalResponse {
    List <ResponseAlmacenDTO> almacenes;
}
