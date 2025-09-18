package com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Response;

import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;

import lombok.Data;
@Data
public class ResponseUnidadMedidaAllDTO  extends GlobalResponse{
    List<ResponseUnidadMedidaDTO> listaCategoria;
}
