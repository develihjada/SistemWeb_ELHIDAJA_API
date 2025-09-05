package com.hidajasac.hidajasac_apis.presentation.dto.nivelAcademicoD;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NivelAcademicoResponseDTO {
    private Long id;
    private String tipoNivel;
    private boolean status;
}
