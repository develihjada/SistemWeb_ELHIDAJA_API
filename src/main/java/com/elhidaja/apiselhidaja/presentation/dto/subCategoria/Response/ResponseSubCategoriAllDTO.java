package com.elhidaja.apiselhidaja.presentation.dto.subCategoria.Response;

import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;

import lombok.Data;
@Data
public class ResponseSubCategoriAllDTO  extends GlobalResponse{
    List<ResponseSubCategoriaDTO> listaSubCategoria;
}
