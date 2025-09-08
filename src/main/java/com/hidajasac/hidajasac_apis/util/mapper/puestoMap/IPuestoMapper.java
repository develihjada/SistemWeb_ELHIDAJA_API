package com.hidajasac.hidajasac_apis.util.mapper.puestoMap;

import com.hidajasac.hidajasac_apis.persistence.entity.area.AreaEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.puesto.PuestoEntity;
import com.hidajasac.hidajasac_apis.presentation.dto.areaD.AreaCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.areaD.AreaResponeDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.areaD.AreaUpdateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.puestoD.PuestoCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.puestoD.PuestoNombreDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.puestoD.PuestoResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.puestoD.PuestoUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IPuestoMapper {

    IPuestoMapper INSTANCE = Mappers.getMapper(IPuestoMapper.class);

    //response all attributes
    //output <-
    //Entity -> ResponseDTO
    PuestoResponseDTO puestoEntityToPuestoResponseDTO(PuestoEntity entity);

    //response some attributes
    //output <-
    // Entity -> ResponseDTO
    PuestoNombreDTO puestoEntityToPuestoNombreDTO(PuestoEntity entity);

    //for new
    //input ->
    //CreateDTO -> Entity
    PuestoEntity puestoCreateDTOToPuestoEntity(PuestoCreateDTO dto);

    //for update name
    //input ->
    //UpdateDTO -> Entity
    void updatePuestoFromDto(PuestoUpdateDTO dto, @MappingTarget PuestoEntity entity);

}
