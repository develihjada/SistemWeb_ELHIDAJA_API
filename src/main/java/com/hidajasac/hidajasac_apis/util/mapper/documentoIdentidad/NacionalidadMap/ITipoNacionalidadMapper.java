package com.hidajasac.hidajasac_apis.util.mapper.documentoIdentidad.NacionalidadMap;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.Nacionalidad.TipoNacionalidadEntity;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD.TipoNacionalidadCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD.TipoNacionalidadResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD.TipoNacionalidadUpdateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD.TipoNacionalidadNombreDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ITipoNacionalidadMapper {
    ITipoNacionalidadMapper INSTANCE = Mappers.getMapper(ITipoNacionalidadMapper.class);

    // TipoNacionalidadEntity -> NacionalidadResponseDTO
    TipoNacionalidadResponseDTO nacionalidadEntityToResponseDTO(TipoNacionalidadEntity entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "tipoNacionalidad", target = "tipoNacionalidad")
    TipoNacionalidadNombreDTO nacionalidadEntityToTipoNacionalidadNombreDTO(TipoNacionalidadEntity entity);

    // NacionalidadCreateDTO -> TipoNacionalidadEntity
    @Mapping(source = "tipoNacionalidad", target = "tipoNacionalidad")
    TipoNacionalidadEntity createDTOToEntity(TipoNacionalidadCreateDTO dto);

    // NacionalidadUpdateDTO -> TipoNacionalidadEntity (actualiza nombre)
    @Mapping(source = "tipoNacionalidad", target = "tipoNacionalidad")
    void updateEntityFromDTO(TipoNacionalidadUpdateDTO dto, @MappingTarget TipoNacionalidadEntity entity);
}
