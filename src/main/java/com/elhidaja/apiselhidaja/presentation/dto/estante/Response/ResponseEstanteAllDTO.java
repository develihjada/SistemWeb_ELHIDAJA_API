package com.elhidaja.apiselhidaja.presentation.dto.estante.Response;

import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "estantes", "exito", "mensaje",  "codigo" })
public class ResponseEstanteAllDTO extends GlobalResponse {
    List <ResponseEstanteDTO> estantes;
}
