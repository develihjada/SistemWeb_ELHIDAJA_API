package com.elhidaja.apiselhidaja.presentation.dto.ubicacion.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUbicacionDTO {
    private Long id;
    private String codigoAlmacen;
    private String codigoEstante;
    private String descripcion;
    private Boolean status;
}
