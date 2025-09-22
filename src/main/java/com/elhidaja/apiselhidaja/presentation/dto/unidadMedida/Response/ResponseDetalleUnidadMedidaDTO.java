package com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "unidadMedida", "exito", "mensaje",  "codigo" })
public class ResponseDetalleUnidadMedidaDTO extends GlobalResponse {
    private ResponseUnidadMedidaDTO unidadMedida;
}
