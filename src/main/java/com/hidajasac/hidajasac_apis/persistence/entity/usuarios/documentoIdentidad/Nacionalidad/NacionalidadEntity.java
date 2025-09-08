package com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.Nacionalidad;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.Documento.DocumentoIdentidadEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "nacionalidad")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NacionalidadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", unique = true, nullable = false, length = 30)
    private String nombre;

    @OneToMany(targetEntity = DocumentoIdentidadEntity.class, fetch = FetchType.LAZY,mappedBy = "nacionalidad")
    @JsonManagedReference
    private List<DocumentoIdentidadEntity> documentoDeIdentidad;

    @Column(name = "status", nullable = false)
    private boolean status = true;
}
