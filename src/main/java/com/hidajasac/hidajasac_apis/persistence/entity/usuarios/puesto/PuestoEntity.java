package com.hidajasac.hidajasac_apis.persistence.entity.usuarios.puesto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "puesto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PuestoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo",unique = true,nullable = false, length = 100)
    private String tipoPuesto;

    @Column(name = "status",nullable = false)
    private boolean status;
}
