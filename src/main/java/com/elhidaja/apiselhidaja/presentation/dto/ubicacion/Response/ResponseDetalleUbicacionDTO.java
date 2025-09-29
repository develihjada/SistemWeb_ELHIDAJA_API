package com.elhidaja.apiselhidaja.presentation.dto.ubicacion.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "ubicacion", "exito", "mensaje",  "codigo" })
public class ResponseDetalleUbicacionDTO extends GlobalResponse {
     private ResponseUbicacionDTO ubicacion;
}
