package com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Response;

import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
@Data
@JsonPropertyOrder({ "unidadMedidas", "exito", "mensaje",  "codigo" })
public class ResponseUnidadMedidaAllDTO  extends GlobalResponse{
    List<ResponseUnidadMedidaDTO> unidadMedidas;
}
