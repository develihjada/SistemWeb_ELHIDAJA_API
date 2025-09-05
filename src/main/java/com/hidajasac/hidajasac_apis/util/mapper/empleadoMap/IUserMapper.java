package com.hidajasac.hidajasac_apis.util.mapper.empleadoMap;

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

    // userEntity -> userEntityToUserResponseDTO
    UserResponseDTO userEntityToUserResponseDTO(UserEntity Entity);

    // UserCreateDTO -> userEntity
    @Mapping(source = "nombres", target = "nombres")
    @Mapping(source = "apellidos", target = "apellidos")
    @Mapping(source = "fechaNacimiento", target = "fechaNacimiento")
    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "direccion", target = "direccion")
    @Mapping(source = "email",target = "email")
    @Mapping(source = "genero", target = "genero")
    @Mapping(source = "estadoCivil", target = "estadoCivil")
    @Mapping(source = "password", target = "password")
    UserEntity EmpleadoEntityCreateDTOToEmpleadoEntity(UserCreateDTO userCreateDTO);

    // UserUpdateDTO -> userEntity
    @Mapping(source = "nombres", target = "nombres")
    @Mapping(source = "apellidos", target = "apellidos")
    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "direccion", target = "direccion")
    @Mapping(source = "email",target = "email")
    @Mapping(source = "estadoCivil", target = "estadoCivil")
    void updateUserFromDto(UserUpdateDTO dto, @MappingTarget UserEntity entity);

}
