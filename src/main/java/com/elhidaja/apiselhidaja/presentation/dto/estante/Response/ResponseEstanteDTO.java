package com.elhidaja.apiselhidaja.presentation.dto.estante.Response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEstanteDTO {
    private Long id;
    private String almacen;
    private String ubicacion;
    private String codigo;
    private String descripcion;
    private Boolean status;
}
