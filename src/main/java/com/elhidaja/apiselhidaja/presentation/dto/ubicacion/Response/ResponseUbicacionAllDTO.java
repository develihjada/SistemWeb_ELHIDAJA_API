package com.elhidaja.apiselhidaja.presentation.dto.ubicacion.Response;

import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;

@Getter
@Setter
@JsonPropertyOrder({ "ubicaciones", "exito", "mensaje",  "codigo" })
public class ResponseUbicacionAllDTO  extends GlobalResponse{
        private List<ResponseUbicacionDTO> ubicaciones;
}
