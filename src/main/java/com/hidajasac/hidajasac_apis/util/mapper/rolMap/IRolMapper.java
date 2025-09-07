package com.hidajasac.hidajasac_apis.util.mapper.rolMap;


import com.hidajasac.hidajasac_apis.persistence.entity.area.AreaEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.rol.RolEntity;
import com.hidajasac.hidajasac_apis.presentation.dto.areaD.AreaNombreDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.rolD.RolCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.rolD.RolNombreDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.rolD.RolResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.rolD.RolUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IRolMapper {

    IRolMapper INSTANCE = Mappers.getMapper(IRolMapper.class);

    //response all attributes
    //output <-
    //Entity -> ResponseDTO
    RolResponseDTO rolEntityToRolResponseDTO(RolEntity rolEntity);

    //response some attributes
    //output <-
    //Entity -> ResponseDTO
    RolNombreDTO areaEntityToAreaNombreDTO(RolEntity entity);

    //for new
    //input ->
    //CreateDTO -> Entity
    RolEntity rolCreateDTOToRolEntity(RolCreateDTO rolCreateDTO);

    //for update name
    //input ->
    //UpdateDTO -> Entity
    void updateRolFromDto(RolUpdateDTO rolUpdateDTO, @MappingTarget RolEntity rolEntity);

}
