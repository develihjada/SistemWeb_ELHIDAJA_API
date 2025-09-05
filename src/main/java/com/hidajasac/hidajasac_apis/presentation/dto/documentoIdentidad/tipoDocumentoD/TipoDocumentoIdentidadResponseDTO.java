package com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.tipoDocumentoD;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class TipoDocumentoIdentidadResponseDTO {
    private Long id;
    private String codigo;
    private String nombre;
    private String validacionRegex;
    private Integer longitudMinima;
    private Integer longitudMaxima;
    private String mensajeError;
    private boolean status;

}
