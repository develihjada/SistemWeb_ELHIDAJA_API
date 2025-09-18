package com.elhidaja.apiselhidaja.persistence.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Categoria {

    private Long idCategoria;
    private String nombre;
    private LocalDateTime fechaCreacion;
    private Boolean status;

}
