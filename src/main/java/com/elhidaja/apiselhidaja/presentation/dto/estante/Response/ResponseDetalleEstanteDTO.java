package com.elhidaja.apiselhidaja.presentation.dto.estante.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "estante", "exito", "mensaje",  "codigo" })
public class ResponseDetalleEstanteDTO extends GlobalResponse {
    private ResponseEstanteDTO estante;
}
