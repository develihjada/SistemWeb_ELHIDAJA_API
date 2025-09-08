package com.hidajasac.hidajasac_apis.presentation.dto.usuarioD;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.enums.EstadoCivil;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 250, message = "El apellido no puede tener más de 250 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El apellido solo puede contener letras y espacios")
    private String nombres;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 250, message = "El apellido no puede tener más de 250 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El apellido solo puede contener letras y espacios")
    private String apellidos;

    @NotNull(message = "El estado civil es obligatorio")
    private EstadoCivil estadoCivil;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(
            regexp = "^[0-9]{7,15}$",
            message = "El teléfono debe contener solo números y tener entre 7 y 15 dígitos"
    )
    private String telefono;

    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(max = 500, message = "La dirección no puede tener más de 500 caracteres")
    private String direccion;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El formato del email no es válido")
    private String email;

    @NotNull(message = "El ID del rol es obligatorio")
    private Long id_rol;

    // Campos específicos de Empleado
    @NotNull(message = "El ID del nombre es obligatorio")
    private Long id_oficio;

    @NotNull(message = "El ID del nombre académico es obligatorio")
    private Long id_nivel_academico;

    @NotNull(message = "El ID del área es obligatoria")
    private Long id_area;

    @NotNull(message = "El ID del puesto es obligatorio")
    private Long id_puesto;

}
