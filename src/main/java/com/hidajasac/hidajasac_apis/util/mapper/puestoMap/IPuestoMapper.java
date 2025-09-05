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
    // PuestoEntity -> PuestoResponseDTO
    PuestoResponseDTO puestoEntityToPuestoResponseDTO(PuestoEntity entity);

    //for two atributes
    //output <-
    @Mapping(source = "id", target = "id")
    @Mapping(source = "tipoPuesto", target = "tipoPuesto")
    PuestoNombreDTO puestoEntityToPuestoNombreDTO(PuestoEntity entity);
    //for new
    //input
    // PuestoCreateDTO -> PuestoEntity
    @Mapping(source = "tipoPuesto", target = "tipoPuesto")
    PuestoEntity puestoCreateDTOToPuestoEntity(PuestoCreateDTO dto);

    //for update name
    //input
    // PuestoUpdateDTO -> PuestoEntity
    @Mapping(source = "tipoPuesto", target = "tipoPuesto")
    void updatePuestoFromDto(PuestoUpdateDTO dto, @MappingTarget PuestoEntity entity);

}
