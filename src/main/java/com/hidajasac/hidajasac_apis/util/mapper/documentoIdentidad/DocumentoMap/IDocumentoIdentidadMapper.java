package com.hidajasac.hidajasac_apis.util.mapper.documentoIdentidad.DocumentoMap;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.Documento.DocumentoIdentidadEntity;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.DocumentoD.DocumentoIdentidadNombreDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.DocumentoD.DocumentoIdentidadCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.DocumentoD.DocumentoIdentidadResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.DocumentoD.DocumentoIdentidadUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IDocumentoIdentidadMapper {

    IDocumentoIdentidadMapper INSTANCE = Mappers.getMapper(IDocumentoIdentidadMapper.class);

    //response all attributes
    //output <-
    //Entity -> ResponseDTO
    DocumentoIdentidadResponseDTO entityToResponseDTO(DocumentoIdentidadEntity entity);

    //response some attributes
    //output <-
    //Entity -> ResponseDTO
    DocumentoIdentidadNombreDTO entityToDocumentoIdentidadNombreDTO(DocumentoIdentidadEntity entity);

    //for new
    //input ->
    //CreateDTO -> Entity
    DocumentoIdentidadEntity createDTOToEntity(DocumentoIdentidadCreateDTO dto);

    //for update
    //input ->
    //UpdateDTO -> Entity
    void updateEntityFromDTO(DocumentoIdentidadUpdateDTO dto, @MappingTarget DocumentoIdentidadEntity entity);

}
