package com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.DocumentoD;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class DocumentoIdentidadResponseDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String tipoDocumento;
    private Integer longitud;
    private boolean status;

}
