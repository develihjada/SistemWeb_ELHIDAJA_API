package com.hidajasac.hidajasac_apis.util.mapper.areaMap;

import com.hidajasac.hidajasac_apis.persistence.entity.area.AreaEntity;
import com.hidajasac.hidajasac_apis.presentation.dto.areaD.AreaCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.areaD.AreaNombreDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.areaD.AreaResponeDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.areaD.AreaUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IAreaMapper {
    IAreaMapper INSTANCE = Mappers.getMapper(IAreaMapper.class);

    //response all attributes
    // AreaEntity -> AreaResponseDTO
    AreaResponeDTO areaEntityToAreaResponseDTO(AreaEntity objAreaEntity);
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombreArea", target = "nombreArea")
    AreaNombreDTO areaEntityToAreaNombreDTO(AreaEntity entity);

    //for new
    //input
    // AreaCreateDTO -> AreaEntity
    @Mapping(source = "nombreArea",target = "nombreArea")
    AreaEntity areaCreateDTOToAreaEntity(AreaCreateDTO objAreaCreateDTO);

    //for update name
    //input
    // AreaUpdateDTO -> AreaEntity
    @Mapping(source = "nombreArea",target = "nombreArea")
    void updateAreaFromDto(AreaUpdateDTO objAreaUpdateDTO ,@MappingTarget AreaEntity areaEntity);

}
