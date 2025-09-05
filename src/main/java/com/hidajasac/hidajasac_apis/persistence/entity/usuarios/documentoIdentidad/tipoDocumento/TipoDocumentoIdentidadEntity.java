package com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.tipoDocumento;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.Nacionalidad.TipoNacionalidadEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.enums.TipoDocumento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "tipo_documento_identidad")
@AllArgsConstructor
@NoArgsConstructor
public  class TipoDocumentoIdentidadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", unique = true, nullable = false, length = 10)
    private String codigo;

    @Enumerated(EnumType.STRING)
    @Column(name = "id_validacionRegex",nullable = false)
    private TipoDocumento validacionRegex;

    @Column(name = "longitud_minima", nullable = false)
    private Integer longitudMinima;

    @Column(name = "longitud_maxima", nullable = false)
    private Integer longitudMaxima;

    @Column(name = "mensaje_error", nullable = false)
    private String mensajeError;

    @Column(name = "status", nullable = false)
    private boolean status = true;

    @Column(name = "desactivado_manualmente", nullable = false)
    private boolean desactivadoManualmente = false;

    @ManyToOne(targetEntity = TipoNacionalidadEntity.class)
    @JoinColumn(name = "tipo_nacionalidad_id", nullable = false)
    @JsonBackReference
    private TipoNacionalidadEntity nacionalidad;
}
