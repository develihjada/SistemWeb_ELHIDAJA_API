package com.hidajasac.hidajasac_apis.presentation.dto.oficioD;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OficioResponseDTO {

    private Long id;
    private String nombre;
    private boolean status;
}
