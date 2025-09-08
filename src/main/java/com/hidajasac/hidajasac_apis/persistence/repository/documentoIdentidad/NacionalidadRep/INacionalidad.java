package com.hidajasac.hidajasac_apis.persistence.repository.documentoIdentidad.NacionalidadRep;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.Nacionalidad.NacionalidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface INacionalidad extends JpaRepository<NacionalidadEntity, Long> {
    //buscar por nombre
    Optional<NacionalidadEntity> findByNombre(String findByNombre);
}
