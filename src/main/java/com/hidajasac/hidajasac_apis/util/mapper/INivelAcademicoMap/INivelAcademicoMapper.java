package com.hidajasac.hidajasac_apis.util.mapper.INivelAcademicoMap;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.nivelAcademico.NivelAcademicoEntity;
import com.hidajasac.hidajasac_apis.presentation.dto.nivelAcademicoD.NivelAcademicoCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.nivelAcademicoD.NivelAcademicoNombreDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.nivelAcademicoD.NivelAcademicoResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.nivelAcademicoD.NivelAcademicoUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface INivelAcademicoMapper {

    INivelAcademicoMapper INSTANCE = Mappers.getMapper(INivelAcademicoMapper.class);

    //response all attributes
    //output <-
    //Entity -> ResponseDTO
    NivelAcademicoResponseDTO nivelEntityToResponseDTO(NivelAcademicoEntity entity);

    //response some attributes
    //output <-
    //Entity -> ResponseDTO
    NivelAcademicoNombreDTO nivelEntityToNivelAcademicoNombreDTO (NivelAcademicoEntity entity);

    //for new
    //input ->
    //CreateDTO -> Entity
    NivelAcademicoEntity createDTOToEntity(NivelAcademicoCreateDTO dto);

    //for update
    //input ->
    //UpdateDTO -> Entity
    void updateFromDto(NivelAcademicoUpdateDTO dto, @MappingTarget NivelAcademicoEntity entity);
}

