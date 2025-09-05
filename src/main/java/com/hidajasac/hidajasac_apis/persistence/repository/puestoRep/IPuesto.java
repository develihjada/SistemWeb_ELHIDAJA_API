package com.hidajasac.hidajasac_apis.persistence.repository.puestoRep;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.puesto.PuestoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPuesto extends JpaRepository<PuestoEntity, Long> {
    Optional<PuestoEntity> findByTipoPuesto(String tipoPuesto);
}