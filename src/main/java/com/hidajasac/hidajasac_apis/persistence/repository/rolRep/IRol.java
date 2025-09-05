package com.hidajasac.hidajasac_apis.persistence.repository.rolRep;
import com.hidajasac.hidajasac_apis.persistence.entity.area.AreaEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.rol.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRol extends JpaRepository<RolEntity, Long> {
    //buscar por tipo
    Optional<RolEntity> findByTipoRol(String tipoRol);

}