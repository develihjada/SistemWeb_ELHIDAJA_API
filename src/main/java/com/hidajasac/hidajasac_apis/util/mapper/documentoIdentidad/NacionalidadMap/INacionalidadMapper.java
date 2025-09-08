package com.hidajasac.hidajasac_apis.util.mapper.documentoIdentidad.NacionalidadMap;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.Nacionalidad.NacionalidadEntity;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD.NacionalidadCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD.NacionalidadNombreDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD.NacionalidadResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD.NacionalidadUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface INacionalidadMapper {

    INacionalidadMapper INSTANCE = Mappers.getMapper(INacionalidadMapper.class);

    //response all attributes
    //output <-
    //Entity -> ResponseDTO
    NacionalidadResponseDTO nacionalidadEntityToResponseDTO(NacionalidadEntity entity);

    //response some attributes
    //output <-
    //Entity -> ResponseDTO
    NacionalidadNombreDTO nacionalidadEntityToTipoNacionalidadNombreDTO(NacionalidadEntity entity);

    //for new
    //input ->
    //CreateDTO -> Entity
    NacionalidadEntity createDTOToEntity(NacionalidadCreateDTO dto);

    //for update
    //input ->
    //UpdateDTO -> Entity
    void updateEntityFromDTO(NacionalidadUpdateDTO dto, @MappingTarget NacionalidadEntity entity);
}
