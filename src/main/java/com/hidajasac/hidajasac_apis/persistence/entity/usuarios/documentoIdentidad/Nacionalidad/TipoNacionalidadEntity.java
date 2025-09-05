package com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.Nacionalidad;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.tipoDocumento.TipoDocumentoIdentidadEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tipo_nacionalidad")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoNacionalidadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_nacionalidad", unique = true, nullable = false, length = 30)
    private String tipoNacionalidad;

    @OneToMany(targetEntity = TipoDocumentoIdentidadEntity.class, fetch = FetchType.LAZY,mappedBy = "nacionalidad")
    @JsonManagedReference
    private List<TipoDocumentoIdentidadEntity> tiposDeDocumento;

    @Column(name = "status", nullable = false)
    private boolean status = true;
}
