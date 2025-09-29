package com.elhidaja.apiselhidaja.presentation.dto.categoria.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "categoria", "exito", "mensaje",  "codigo" })
public class ResponseDetalleCategoriaDTO extends GlobalResponse {
    private ResponseCategoriaDTO categoria;
}
