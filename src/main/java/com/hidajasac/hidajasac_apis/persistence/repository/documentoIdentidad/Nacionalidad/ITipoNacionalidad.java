package com.hidajasac.hidajasac_apis.persistence.repository.documentoIdentidad.Nacionalidad;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.Nacionalidad.TipoNacionalidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITipoNacionalidad extends JpaRepository<TipoNacionalidadEntity, Long> {
    Optional<TipoNacionalidadEntity> findByTipoNacionalidad(String tipoNacionalidad);
}
