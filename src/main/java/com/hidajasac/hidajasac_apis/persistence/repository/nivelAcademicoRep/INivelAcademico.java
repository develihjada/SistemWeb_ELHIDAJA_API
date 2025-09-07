package com.hidajasac.hidajasac_apis.persistence.repository.nivelAcademicoRep;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.nivelAcademico.NivelAcademicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface INivelAcademico extends JpaRepository<NivelAcademicoEntity, Long> {
    //buscar por nombre
    Optional<NivelAcademicoEntity> findByNombre(String nombre);
}
