package com.hidajasac.hidajasac_apis.persistence.repository.documentoIdentidad.DocumentoRep;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.Documento.DocumentoIdentidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDocumentoIdentidad extends JpaRepository<DocumentoIdentidadEntity, Long> {

    //buscar por nombre
    Optional<DocumentoIdentidadEntity> findByNombre(String nombre);
    //buscar por id nacionalidad
    List<DocumentoIdentidadEntity> findByNacionalidadId(Long nacionalidad);
    //para buscar por id nacionalidad y que estea activa
    List<DocumentoIdentidadEntity> findByNacionalidadIdAndStatusTrue(Long nacionalidad);
}
