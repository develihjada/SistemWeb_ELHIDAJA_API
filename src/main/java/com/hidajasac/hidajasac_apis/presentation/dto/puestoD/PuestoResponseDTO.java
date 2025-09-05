package com.hidajasac.hidajasac_apis.presentation.dto.puestoD;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PuestoResponseDTO {
    private Long id;
    private String tipoPuesto;
    private boolean status;
}
