package com.hidajasac.hidajasac_apis.presentation.dto.usuarioD;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.enums.EstadoCivil;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.enums.Genero;
import com.hidajasac.hidajasac_apis.presentation.dto.areaD.AreaNombreDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.tipoDocumentoD.TipoDocumentoIdentidadNombreDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD.TipoNacionalidadNombreDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.nivelAcademicoD.NivelAcademicoNombreDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.oficioD.OficioNombreDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.puestoD.PuestoNombreDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.rolD.RolNombreDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private  Long id;

    private String nombres;

    private String apellidos;

    private LocalDate fechaNacimiento;

    private EstadoCivil estadoCivil;

    private String telefono;

    private String direccion;

    private String dni;

    private Boolean status;

    private Genero genero;

    private String email;

    private LocalDateTime fechaCreacion;

    private RolNombreDTO tipoRol;

    private TipoDocumentoIdentidadNombreDTO tipoDocumento;

    private TipoNacionalidadNombreDTO Tiponacionalidad;

    private OficioNombreDTO oficio;

    private NivelAcademicoNombreDTO nivelAcademico;

    private AreaNombreDTO area;

    private PuestoNombreDTO puesto;
}
