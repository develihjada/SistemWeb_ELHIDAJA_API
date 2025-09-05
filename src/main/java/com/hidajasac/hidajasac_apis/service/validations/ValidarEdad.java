package com.hidajasac.hidajasac_apis.service.validations;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.usuario.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class ValidarEdad {

    /**
     * Valida que la fecha de nacimiento sea al menos 18 años antes de la fecha actual
     * @param user entidad UserEntity a validar
     */
    public void validarMayorDeEdad(UserEntity user) {
        LocalDate fechaNacimiento = user.getFechaNacimiento();
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula.");
        }
        LocalDate fechaMinima = LocalDate.now().minusYears(18);
        if (fechaNacimiento.isAfter(fechaMinima)) {
            throw new IllegalArgumentException("El usuario debe ser mayor de 18 años.");
        }
    }
}
