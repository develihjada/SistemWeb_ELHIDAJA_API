package com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD;

import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.DocumentoD.DocumentoIdentidadNombreDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NacionalidadResponseDTO {

    private Long id;

    private String nombre;

    private List<DocumentoIdentidadNombreDTO> documentoDeIdentidad;

    private boolean status;
}
