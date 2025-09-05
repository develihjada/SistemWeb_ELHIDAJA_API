package com.hidajasac.hidajasac_apis.persistence.entity.usuarios.rol;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "rol")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column (name = "tipo_rol",nullable = false,unique = true, length = 50)
    private String tipoRol;

    @Column(name = "status", nullable = false)
    private boolean status=true;
}
