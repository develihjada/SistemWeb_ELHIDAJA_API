package com.elhidaja.apiselhidaja.presentation.dto.categoria.Response;

import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ResponseCategoriAllDTO  extends GlobalResponse{
    List<ResponseCategoriaDTO> listaCategoria;
}
