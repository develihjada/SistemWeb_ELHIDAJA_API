package com.hidajasac.hidajasac_apis.util.mapper.oficioMap;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.oficio.OficioEntity;
import com.hidajasac.hidajasac_apis.presentation.dto.oficioD.OficioCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.oficioD.OficioNombreDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.oficioD.OficioResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.oficioD.OficioUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IOficioMapper {

    IOficioMapper INSTANCE = Mappers.getMapper(IOficioMapper.class);

    //response all attributes
    //output <-
    // OficioEntity -> OficioResponseDTO
    OficioResponseDTO oficioEntityToResponseDTO(OficioEntity oficioEntity);

    //for two atributes
    //output <-
    @Mapping(source = "id", target = "id")
    @Mapping(source = "tipoOficio", target = "tipoOficio")
    OficioNombreDTO oficioEntityToOficioNombreDTO(OficioEntity oficioEntity);

    //for new
    //input
    // OficioCreateDTO -> OficioEntity
    @Mapping(source = "tipoOficio", target = "tipoOficio")
    OficioEntity oficioCreateDTOToEntity(OficioCreateDTO oficioCreateDTO);

    //for update
    //input
    // OficioUpdateDTO ->  OficioEntity
    @Mapping(source = "tipoOficio", target = "tipoOficio")
    void updateOficioFromDTO(OficioUpdateDTO dto, @MappingTarget OficioEntity entity);

}
