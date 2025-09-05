package com.hidajasac.hidajasac_apis.persistence.entity.usuarios.oficio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "oficio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OficioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo",unique = true,nullable = false, length = 50)
    private String tipoOficio;

    @Column(name = "status", nullable = false)
    private boolean status;
}
