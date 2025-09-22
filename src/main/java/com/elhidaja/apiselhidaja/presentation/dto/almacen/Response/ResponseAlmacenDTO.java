package com.elhidaja.apiselhidaja.presentation.dto.almacen.Response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAlmacenDTO {
    private Long id_almacen;
    private String codigo;
    private String descripcion;
    private Boolean status;
}
