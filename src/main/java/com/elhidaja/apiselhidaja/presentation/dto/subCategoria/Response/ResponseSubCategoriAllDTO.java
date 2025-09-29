package com.elhidaja.apiselhidaja.presentation.dto.subCategoria.Response;

import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
@Data
@JsonPropertyOrder({ "subCategorias", "exito", "mensaje",  "codigo" })
public class ResponseSubCategoriAllDTO  extends GlobalResponse{
    List<ResponseSubCategoriaDTO> subCategorias;
}
