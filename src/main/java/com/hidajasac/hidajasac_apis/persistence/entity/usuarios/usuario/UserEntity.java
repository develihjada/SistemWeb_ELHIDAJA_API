package com.hidajasac.hidajasac_apis.persistence.entity.usuarios.usuario;

import com.hidajasac.hidajasac_apis.persistence.entity.area.AreaEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.Nacionalidad.NacionalidadEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.Documento.DocumentoIdentidadEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.nivelAcademico.NivelAcademicoEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.oficio.OficioEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.puesto.PuestoEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.rol.RolEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.enums.EstadoCivil;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.enums.Genero;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "id_estado_civil", nullable = false)
    private EstadoCivil estadoCivil;

    @Column(name = "telefono", unique = true, nullable = false)
    private String telefono;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_documento", nullable = false)
    private DocumentoIdentidadEntity documento;

    @Column(name = "numero_documento", unique = true, nullable = false)
    private String numeroDocumento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nacionalidad", nullable = false)
    private NacionalidadEntity nacionalidad;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Enumerated(EnumType.STRING)
    @Column(name = "id_genero", nullable = false)
    private Genero genero;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private RolEntity rol;

    @Column(name = "fecha_creacion", updatable = false, nullable = false)
    private LocalDateTime fechaCreacion;

    @OneToOne
    @JoinColumn(name = "id_oficio", nullable = false)
    private OficioEntity oficio;

    @OneToOne
    @JoinColumn(name = "id_nivel_academico", nullable = false)
    private NivelAcademicoEntity nivelAcademico;

    @OneToOne
    @JoinColumn(name = "id_area", nullable = false)
    private AreaEntity area;

    @OneToOne
    @JoinColumn(name = "id_puesto", nullable = false)
    private PuestoEntity puesto;

}

