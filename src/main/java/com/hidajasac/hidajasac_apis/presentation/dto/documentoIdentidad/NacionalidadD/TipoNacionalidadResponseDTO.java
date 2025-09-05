package com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.tipoDocumento.TipoDocumentoIdentidadEntity;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.tipoDocumentoD.TipoDocumentoIdentidadNombreDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.tipoDocumentoD.TipoDocumentoIdentidadUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoNacionalidadResponseDTO {

    private Long id;

    private String tipoNacionalidad;

    private List<TipoDocumentoIdentidadNombreDTO> tiposDeDocumento;

    private boolean status;
}
