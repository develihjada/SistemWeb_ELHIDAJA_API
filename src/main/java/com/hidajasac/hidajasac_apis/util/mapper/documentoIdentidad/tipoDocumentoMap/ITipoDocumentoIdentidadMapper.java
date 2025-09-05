package com.hidajasac.hidajasac_apis.util.mapper.documentoIdentidad.tipoDocumentoMap;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.tipoDocumento.TipoDocumentoIdentidadEntity;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.tipoDocumentoD.TipoDocumentoIdentidadNombreDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.tipoDocumentoD.TipoDocumentoIdentidadCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.tipoDocumentoD.TipoDocumentoIdentidadResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.tipoDocumentoD.TipoDocumentoIdentidadUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ITipoDocumentoIdentidadMapper {
    ITipoDocumentoIdentidadMapper INSTANCE = Mappers.getMapper(ITipoDocumentoIdentidadMapper.class);

    TipoDocumentoIdentidadResponseDTO entityToResponseDTO(TipoDocumentoIdentidadEntity entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "codigo", target = "codigo")
    TipoDocumentoIdentidadNombreDTO entityToTipoDocumentoIdentidadNombreDTO(TipoDocumentoIdentidadEntity entity);

    @Mapping(source = "codigo", target = "codigo")
    @Mapping(source = "validacionRegex", target = "validacionRegex")
    @Mapping(source = "longitudMinima", target = "longitudMinima")
    @Mapping(source = "longitudMaxima", target = "longitudMaxima")
    @Mapping(source = "mensajeError", target = "mensajeError")
    TipoDocumentoIdentidadEntity createDTOToEntity(TipoDocumentoIdentidadCreateDTO dto);

    @Mapping(source = "codigo", target = "codigo")
    @Mapping(source = "validacionRegex", target = "validacionRegex")
    @Mapping(source = "longitudMinima", target = "longitudMinima")
    @Mapping(source = "longitudMaxima", target = "longitudMaxima")
    @Mapping(source = "mensajeError", target = "mensajeError")
    void updateEntityFromDTO(TipoDocumentoIdentidadUpdateDTO dto, @MappingTarget TipoDocumentoIdentidadEntity entity);

}
