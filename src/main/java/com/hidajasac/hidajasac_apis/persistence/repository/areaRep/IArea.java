package com.hidajasac.hidajasac_apis.persistence.repository.areaRep;

import com.hidajasac.hidajasac_apis.persistence.entity.area.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IArea extends JpaRepository<AreaEntity,Long> {
    //buscar por tipo_rol
    Optional<AreaEntity> findByNombreArea(String tipo_rol);
}
