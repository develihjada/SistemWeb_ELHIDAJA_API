package com.elhidaja.apiselhidaja.presentation.dto.categoria.Response;

import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@JsonPropertyOrder({ "categorias", "exito", "mensaje",  "codigo" })
public class ResponseCategoriAllDTO  extends GlobalResponse{
    List<ResponseCategoriaDTO> categorias;
}
