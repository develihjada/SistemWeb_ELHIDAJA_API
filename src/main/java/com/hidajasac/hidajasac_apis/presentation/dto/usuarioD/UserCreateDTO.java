package com.hidajasac.hidajasac_apis.presentation.dto.usuarioD;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.enums.EstadoCivil;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.enums.Genero;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 250, message = "El apellido no puede tener más de 250 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El apellido solo puede contener letras y espacios")
    private String nombres;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 250, message = "El apellido no puede tener más de 250 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El apellido solo puede contener letras y espacios")
    private String apellidos;

    @Past(message = "La fecha de nacimiento debe ser una fecha pasada.")
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;

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

    @NotNull(message = "El ID del tipo de documento es obligatorio")
    private Long id_tipo_documento;

    @NotBlank(message = "El número de documento no puede estar vacío")
    @Size(max = 20, message = "El número de documento no puede tener más de 20 caracteres")
    private String numeroDocumento;

    @NotNull(message = "El ID de la nacionalidad es obligatorio")
    private Long id_nacionalidad;

    @NotNull(message = "El género es obligatorio")
    private Genero genero;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El formato del email no es válido")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, max = 100, message = "La contraseña debe tener entre 8 y 100 caracteres")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&\\-_.])[A-Za-z\\d@$!%*?&\\-_.]{8,}$",
            message = "La contraseña debe tener al menos una mayúscula, una minúscula, un número y un carácter especial"
    )
    private String password;

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
