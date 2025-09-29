package com.elhidaja.apiselhidaja.presentation.dto.producto.Response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductoDTO {

    private Long id;
    private String categoria;
    private String subCategoria;
    private String nombre;
    private String codigo;
    private String codigoBarras;
    private Integer stock;
    private String unidadMedida;
    private String pallet;
    private String estante;
    private String almacen;
    private Boolean status;
}
