package com.elhidaja.apiselhidaja.persistence.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCateoria {

    private Long id_sub_categoria;
    private String nombre;
    private LocalDateTime fecha_creacion;
    private Boolean status;
    private Long id_categoria; 
}
