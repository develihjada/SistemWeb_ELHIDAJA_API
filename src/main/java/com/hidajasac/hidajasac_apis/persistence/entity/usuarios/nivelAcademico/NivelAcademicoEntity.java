package com.hidajasac.hidajasac_apis.persistence.entity.usuarios.nivelAcademico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nivel_academico")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NivelAcademicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_nivel",unique = true,nullable = false, length = 100)
    private String tipoNivel;

    @Column(name = "status",nullable = false)
    private boolean status=true;
}
