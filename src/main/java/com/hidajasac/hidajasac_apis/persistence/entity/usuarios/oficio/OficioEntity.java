package com.hidajasac.hidajasac_apis.persistence.entity.usuarios.oficio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nombre")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OficioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre",unique = true,nullable = false, length = 50)
    private String nombre;

    @Column(name = "status", nullable = false)
    private boolean status;
}
