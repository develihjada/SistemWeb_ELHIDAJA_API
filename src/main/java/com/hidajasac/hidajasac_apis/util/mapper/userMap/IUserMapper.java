package com.hidajasac.hidajasac_apis.util.mapper.userMap;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.usuario.UserEntity;
import com.hidajasac.hidajasac_apis.presentation.dto.usuarioD.UserCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.usuarioD.UserUpdateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.usuarioD.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    //response all attributes
    //output <-
    //Entity -> ResponseDTO
    UserResponseDTO userEntityToUserResponseDTO(UserEntity Entity);

    //for new
    //input ->
    //CreateDTO -> Entity
    UserEntity EmpleadoEntityCreateDTOToEmpleadoEntity(UserCreateDTO userCreateDTO);

    //for update
    //input ->
    //UpdateDTO -> Entity
    void updateUserFromDto(UserUpdateDTO dto, @MappingTarget UserEntity entity);

}
