package com.hidajasac.hidajasac_apis.util.mapper.areaMap;

import com.hidajasac.hidajasac_apis.persistence.entity.area.AreaEntity;
import com.hidajasac.hidajasac_apis.presentation.dto.areaD.AreaCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.areaD.AreaNombreDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.areaD.AreaResponeDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.areaD.AreaUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IAreaMapper {

    IAreaMapper INSTANCE = Mappers.getMapper(IAreaMapper.class);

    //response all attributes
    //output <-
    //Entity -> ResponseDTO
    AreaResponeDTO areaEntityToAreaResponseDTO(AreaEntity objAreaEntity);

    //response some attributes
    //output <-
    //Entity -> ResponseDTO
    AreaNombreDTO areaEntityToAreaNombreDTO(AreaEntity entity);

    //for new
    //input ->
    //CreateDTO -> Entity
    AreaEntity areaCreateDTOToAreaEntity(AreaCreateDTO objAreaCreateDTO);

    //for update
    //input ->
    //UpdateDTO -> Entity
    void updateAreaFromDto(AreaUpdateDTO objAreaUpdateDTO ,@MappingTarget AreaEntity areaEntity);

}
