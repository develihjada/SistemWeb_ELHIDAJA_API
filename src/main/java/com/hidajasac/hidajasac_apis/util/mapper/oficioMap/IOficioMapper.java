package com.hidajasac.hidajasac_apis.util.mapper.oficioMap;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.oficio.OficioEntity;
import com.hidajasac.hidajasac_apis.presentation.dto.oficioD.OficioCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.oficioD.OficioNombreDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.oficioD.OficioResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.oficioD.OficioUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IOficioMapper {

    IOficioMapper INSTANCE = Mappers.getMapper(IOficioMapper.class);

    //response all attributes
    //output <-
    //Entity -> ResponseDTO
    OficioResponseDTO oficioEntityToResponseDTO(OficioEntity oficioEntity);

    //response some attributes
    //output <-
    //Entity -> ResponseDTO
    OficioNombreDTO oficioEntityToOficioNombreDTO(OficioEntity oficioEntity);

    //for new
    //input ->
    //CreateDTO -> Entity
    OficioEntity oficioCreateDTOToEntity(OficioCreateDTO oficioCreateDTO);

    //for update
    //input ->
    //UpdateDTO -> Entity
    void updateOficioFromDTO(OficioUpdateDTO dto, @MappingTarget OficioEntity entity);

}
