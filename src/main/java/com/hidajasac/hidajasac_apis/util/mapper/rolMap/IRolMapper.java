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
    // RolEntity -> RolResponseDTO
    RolResponseDTO rolEntityToRolResponseDTO(RolEntity rolEntity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "tipoRol", target = "tipoRol")
    RolNombreDTO areaEntityToAreaNombreDTO(RolEntity entity);
    //for new
    //input
    // RolCreateDTO -> RolEntity
    @Mapping(source = "tipoRol", target = "tipoRol")
    RolEntity rolCreateDTOToRolEntity(RolCreateDTO rolCreateDTO);

    //for update tipo_rol
    //input
    @Mapping(source = "tipoRol", target = "tipoRol")
    void updateRolFromDto(RolUpdateDTO rolUpdateDTO, @MappingTarget RolEntity rolEntity);

}
