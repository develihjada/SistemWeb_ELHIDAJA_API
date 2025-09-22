package com.elhidaja.apiselhidaja.presentation.dto.subCategoria.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "subCategoria", "exito", "mensaje",  "codigo" })
public class ResponseDetalleSubCategoriaDTO extends GlobalResponse {
    private ResponseSubCategoriaDTO subCategoria;
}
