package com.hidajasac.hidajasac_apis.persistence.repository.userRep;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.usuario.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUser extends JpaRepository<UserEntity, Long> {
    //buscar usuario por telefono
    Optional<UserEntity> findByTelefono(String telefono);
    //buscar usuario por numero de documento
    Optional<UserEntity> findByNumeroDocumento(String numeroDocumento);
    //buscar usuario por email
    Optional<UserEntity> findByEmail(String email);
    //buscar nacionalidad que este activa
    boolean existsByNacionalidadIdAndStatusTrue(Long nacionalidadId);
    //buscar documento que este activo
    boolean existsByDocumentoIdAndStatusTrue(Long nacionalidadId);
    //buscar rol que este activo
    boolean existsByRolIdAndStatusTrue(Long rolId);
    //buscar oficio que este activo
    boolean existsByOficioIdAndStatusTrue(Long oficioId);
    //buscar nivel academico que este activo
    boolean existsByNivelAcademicoIdAndStatusTrue(Long rolId);
    //buscar area que este activa
    boolean existsByAreaIdAndStatusTrue(Long areaId);

}

