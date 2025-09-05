package com.hidajasac.hidajasac_apis.persistence.repository.documentoIdentidad.tipoDocumento;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.tipoDocumento.TipoDocumentoIdentidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITipoDocumentoIdentidad extends JpaRepository<TipoDocumentoIdentidadEntity, Long> {
    Optional<TipoDocumentoIdentidadEntity> findByCodigo(String codigo);
    List<TipoDocumentoIdentidadEntity> findByNacionalidadId(Long nacionalidad);
    List<TipoDocumentoIdentidadEntity> findByNacionalidadIdAndStatusTrue(Long nacionalidad);
}
