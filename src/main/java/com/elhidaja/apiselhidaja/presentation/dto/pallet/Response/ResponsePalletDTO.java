package com.elhidaja.apiselhidaja.presentation.dto.pallet.Response;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePalletDTO {
    private Long id;
    private String almacen;
    private String estante;
    private String codigo;
    private String descripcion;
    private Boolean status;
}
