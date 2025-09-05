package com.hidajasac.hidajasac_apis.persistence.repository.usuarioRep;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.usuario.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUser extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByTelefono(String telefono);

    Optional<UserEntity> findByNumeroDocumento(String numeroDocumento);

    Optional<UserEntity> findByEmail(String email);

    boolean existsByTiponacionalidadIdAndStatusTrue(Long nacionalidadId);

    boolean existsByTipoDocumentoIdAndStatusTrue(Long nacionalidadId);

    boolean existsByTipoRolIdAndStatusTrue(Long rolId);

    boolean existsByOficioIdAndStatusTrue(Long oficioId);

    boolean existsByNivelAcademicoIdAndStatusTrue(Long rolId);

    boolean existsByAreaIdAndStatusTrue(Long areaId);

}

