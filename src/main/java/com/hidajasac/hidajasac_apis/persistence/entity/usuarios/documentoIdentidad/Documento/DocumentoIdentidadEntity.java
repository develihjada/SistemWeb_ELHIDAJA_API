package com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.Documento;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.Nacionalidad.NacionalidadEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.enums.TipoDocumento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "documento_identidad")
@AllArgsConstructor
@NoArgsConstructor
public  class DocumentoIdentidadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", unique = true, nullable = false, length = 10)
    private String nombre;

    @Column(name = "descripcion", unique = true, nullable = false, length = 10)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento",nullable = false)
    private TipoDocumento tipoDocumento;

    @Column(name = "longitud_maxima", nullable = false)
    private Integer longitud;

    @Column(name = "mensaje_error", nullable = false)
    private String mensajeError;

    @Column(name = "status", nullable = false)
    private boolean status = true;

    @Column(name = "desactivado_manualmente", nullable = false)
    private boolean desactivadoManualmente = false;

    @ManyToOne(targetEntity = NacionalidadEntity.class)
    @JoinColumn(name = "id_nacionalidad", nullable = false)
    @JsonBackReference
    private NacionalidadEntity nacionalidad;
}
