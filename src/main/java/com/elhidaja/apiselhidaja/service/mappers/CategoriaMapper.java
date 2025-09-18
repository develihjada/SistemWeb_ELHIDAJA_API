package com.elhidaja.apiselhidaja.service.mappers;

import com.elhidaja.apiselhidaja.persistence.entity.Categoria;
import com.elhidaja.apiselhidaja.presentation.dto.categoria.Response.ResponseCategoriaDTO;
import com.elhidaja.apiselhidaja.presentation.dto.categoria.Resquest.RequestCategoriaInsertDTO;

public class CategoriaMapper {
    
    public static ResponseCategoriaDTO toResponseDTO(Categoria categoria) {
        if (categoria == null) {
            return null;
        }
        ResponseCategoriaDTO dto = new ResponseCategoriaDTO();
        dto.setId_categoria(categoria.getIdCategoria());
        dto.setNombre(categoria.getNombre());
        dto.setStatus(categoria.getStatus());
        return dto;
    }

    public static Categoria toEntity(RequestCategoriaInsertDTO dto) {
         if (dto == null) {
            return null;
        }
        Categoria categoria = new Categoria();
        categoria.setNombre(dto.getNombre());
        return categoria;
    }
}
