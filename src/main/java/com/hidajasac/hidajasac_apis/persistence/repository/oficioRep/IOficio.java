package com.hidajasac.hidajasac_apis.persistence.repository.oficioRep;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.oficio.OficioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOficio extends JpaRepository<OficioEntity, Long> {
    Optional<OficioEntity> findByTipoOficio(String tipoOficio);
}
