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

    NivelAcademicoResponseDTO nivelEntityToResponseDTO(NivelAcademicoEntity entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "tipoNivel", target = "tipoNivel")
    NivelAcademicoNombreDTO nivelEntityToNivelAcademicoNombreDTO (NivelAcademicoEntity entity);

    @Mapping(source = "tipoNivel", target = "tipoNivel")
    NivelAcademicoEntity createDTOToEntity(NivelAcademicoCreateDTO dto);

    @Mapping(source = "tipoNivel", target = "tipoNivel")
    void updateFromDto(NivelAcademicoUpdateDTO dto, @MappingTarget NivelAcademicoEntity entity);
}

