package com.hidajasac.hidajasac_apis.service.implementacion.userImp;


import com.hidajasac.hidajasac_apis.persistence.entity.area.AreaEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.Nacionalidad.TipoNacionalidadEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.tipoDocumento.TipoDocumentoIdentidadEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.nivelAcademico.NivelAcademicoEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.oficio.OficioEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.puesto.PuestoEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.rol.RolEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.usuario.UserEntity;
import com.hidajasac.hidajasac_apis.persistence.repository.areaRep.IArea;
import com.hidajasac.hidajasac_apis.persistence.repository.documentoIdentidad.Nacionalidad.ITipoNacionalidad;
import com.hidajasac.hidajasac_apis.persistence.repository.documentoIdentidad.tipoDocumento.ITipoDocumentoIdentidad;
import com.hidajasac.hidajasac_apis.persistence.repository.usuarioRep.IUser;
import com.hidajasac.hidajasac_apis.persistence.repository.nivelAcademicoRep.INivelAcademico;
import com.hidajasac.hidajasac_apis.persistence.repository.oficioRep.IOficio;
import com.hidajasac.hidajasac_apis.persistence.repository.puestoRep.IPuesto;
import com.hidajasac.hidajasac_apis.persistence.repository.rolRep.IRol;
import com.hidajasac.hidajasac_apis.presentation.dto.usuarioD.UserResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.usuarioD.UserCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.usuarioD.UserUpdateDTO;
import com.hidajasac.hidajasac_apis.service.exeption.InvalidStateException;
import com.hidajasac.hidajasac_apis.service.exeption.ResourceAlreadyExistsException;
import com.hidajasac.hidajasac_apis.service.validations.validateDocumentoPorNacionalidad;
import com.hidajasac.hidajasac_apis.util.mapper.empleadoMap.IUserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserImp {

    private final IUser repUser;
    private final PasswordEncoder passwordEncoder;
    private final IUserMapper empleadoMapper;
    private final ITipoDocumentoIdentidad tipoDocumentoRepo;
    private final ITipoNacionalidad nacionalidadRepo;
    private final IRol rolRepo;
    private final IOficio oficioRepo;
    private final INivelAcademico nivelAcademicoRepo;
    private final IArea areaRepo;
    private final IPuesto puestoRepo;
    private final validateDocumentoPorNacionalidad validarDoc;


    public UserImp(
            IUser repUser,
            PasswordEncoder passwordEncoder,
            IUserMapper empleadoMapper,
            ITipoDocumentoIdentidad tipoDocumentoRepo,
            ITipoNacionalidad nacionalidadRepo,
            IRol rolRepo,
            IOficio oficioRepo,
            INivelAcademico nivelAcademicoRepo,
            IArea areaRepo,
            IPuesto puestoRepo, validateDocumentoPorNacionalidad validarDoc
    ) {
        this.repUser = repUser;
        this.passwordEncoder = passwordEncoder;
        this.empleadoMapper = empleadoMapper;
        this.tipoDocumentoRepo = tipoDocumentoRepo;
        this.nacionalidadRepo = nacionalidadRepo;
        this.rolRepo = rolRepo;
        this.oficioRepo = oficioRepo;
        this.nivelAcademicoRepo = nivelAcademicoRepo;
        this.areaRepo = areaRepo;
        this.puestoRepo = puestoRepo;
        this.validarDoc = validarDoc;
    }

    // findAll
    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllS() {
        List<UserResponseDTO> response = new ArrayList<>();
        for (UserEntity entity : repUser.findAll()) {
            response.add(IUserMapper.INSTANCE.userEntityToUserResponseDTO(entity));
        }
        return response;
    }

    // findById
    @Transactional(readOnly = true)
    public UserResponseDTO getByIdS(Long id) {
        UserEntity user = repUser.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El user con ID " + id + " no fue encontrado"));
        return IUserMapper.INSTANCE.userEntityToUserResponseDTO(user);
    }

    // create
    @Transactional
    public UserResponseDTO saveSer(UserCreateDTO userCreateDTO) {
        // Validar unicidad de campos
        validateUniqueFields(userCreateDTO.getTelefono(), userCreateDTO.getNumeroDocumento(), userCreateDTO.getEmail());

        // Verificar existencia de las entidades por ID
        TipoDocumentoIdentidadEntity tipoDocumento = tipoDocumentoRepo.findById(userCreateDTO.getId_tipo_documento())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de documento no encontrado"));

        if (!tipoDocumento.isStatus()) {
            throw new InvalidStateException("Este documento está desactivado");
        }

        validarDoc.validateDocumentoPorNacional(userCreateDTO.getId_nacionalidad(), userCreateDTO.getId_tipo_documento(), userCreateDTO.getNumeroDocumento());

        TipoNacionalidadEntity nacionalidad = nacionalidadRepo.findById(userCreateDTO.getId_nacionalidad())
                .orElseThrow(() -> new EntityNotFoundException("Nacionalidad no encontrada"));

        if (!nacionalidad.isStatus()) {
            throw new InvalidStateException("Esta nacionalidad  está desactivada");
        }

        RolEntity rol = rolRepo.findById(userCreateDTO.getId_rol())
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado"));

        if (!rol.isStatus()) {
            throw new InvalidStateException("Este de rol  está desactivado");
        }

        OficioEntity oficio = oficioRepo.findById(userCreateDTO.getId_oficio())
                .orElseThrow(() -> new EntityNotFoundException("Oficio no encontrado"));

        if (!oficio.isStatus()) {
            throw new InvalidStateException("Este de oficio  está desactivado");
        }

        NivelAcademicoEntity nivel = nivelAcademicoRepo.findById(userCreateDTO.getId_nivel_academico())
                .orElseThrow(() -> new EntityNotFoundException("Nivel académico no encontrado"));

        if (!nivel.isStatus()) {
            throw new InvalidStateException("Este de nivel academico  está desactivado");
        }

        AreaEntity area = areaRepo.findById(userCreateDTO.getId_area())
                .orElseThrow(() -> new EntityNotFoundException("Área no encontrada"));

        if (!area.isStatus()) {
            throw new InvalidStateException("Esta de area   está desactivada");
        }

        PuestoEntity puesto = puestoRepo.findById(userCreateDTO.getId_puesto())
                .orElseThrow(() -> new EntityNotFoundException("Puesto no encontrado"));

        if (!puesto.isStatus()) {
            throw new InvalidStateException("Este de puesto   está desactivado");
        }

        // Usar el mapper para mapear el DTO a entidad
        UserEntity userEntity = empleadoMapper.EmpleadoEntityCreateDTOToEmpleadoEntity(userCreateDTO);

        // Setear entidades verificadas manualmente
        userEntity.setTipoDocumento(tipoDocumento);
        userEntity.setTiponacionalidad(nacionalidad);
        userEntity.setNumeroDocumento(userCreateDTO.getNumeroDocumento());
        userEntity.setTipoRol(rol);
        userEntity.setOficio(oficio);
        userEntity.setNivelAcademico(nivel);
        userEntity.setArea(area);
        userEntity.setPuesto(puesto);

        // Encriptar contraseña
        userEntity.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));

        // Asignar valores por defecto
        userEntity.setStatus(true);
        userEntity.setFechaCreacion(LocalDateTime.now());

        // Persistir la entidad
        UserEntity savedEmpleado = repUser.save(userEntity);
        return IUserMapper.INSTANCE.userEntityToUserResponseDTO(savedEmpleado);
    }

    // update
    @Transactional
    public UserResponseDTO updateSer(UserUpdateDTO userUpdateDTO, Long id) {
        // el empleado existe
        UserEntity userEntity = repUser.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El empleado con ID " + id + " no fue encontrado"));

        //
        if (!userEntity.getTelefono().equals(userUpdateDTO.getTelefono())) {
            repUser.findByTelefono(userUpdateDTO.getTelefono()).ifPresent(e -> {
                throw new ResourceAlreadyExistsException("El teléfono '" + userUpdateDTO.getTelefono() + "' ya está en uso.");
            });
        }
        if (!userEntity.getEmail().equals(userUpdateDTO.getEmail())) {
            repUser.findByEmail(userUpdateDTO.getEmail()).ifPresent(e -> {
                throw new ResourceAlreadyExistsException("El email '" + userUpdateDTO.getEmail() + "' ya está en uso.");
            });
        }

        //
        RolEntity rol = rolRepo.findById(userUpdateDTO.getId_rol())
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado"));

        if (!rol.isStatus()) {
            throw new InvalidStateException("Este de rol  está desactivado");
        }

        OficioEntity oficio = oficioRepo.findById(userUpdateDTO.getId_oficio())
                .orElseThrow(() -> new EntityNotFoundException("Oficio no encontrado"));

        if (!oficio.isStatus()) {
            throw new InvalidStateException("Este de oficio  está desactivado");
        }

        NivelAcademicoEntity nivel = nivelAcademicoRepo.findById(userUpdateDTO.getId_nivel_academico())
                .orElseThrow(() -> new EntityNotFoundException("Nivel académico no encontrado"));

        if (!nivel.isStatus()) {
            throw new InvalidStateException("Este de nivel academico  está desactivado");
        }

        AreaEntity area = areaRepo.findById(userUpdateDTO.getId_area())
                .orElseThrow(() -> new EntityNotFoundException("Área no encontrada"));

        if (!area.isStatus()) {
            throw new InvalidStateException("Esta de area   está desactivada");
        }

        PuestoEntity puesto = puestoRepo.findById(userUpdateDTO.getId_puesto())
                .orElseThrow(() -> new EntityNotFoundException("Puesto no encontrado"));

        if (!puesto.isStatus()) {
            throw new InvalidStateException("Este de puesto   está desactivado");
        }

        // 4. Usar el mapper para actualizar los campos simples desde el DTO
        empleadoMapper.updateUserFromDto(userUpdateDTO, userEntity);

        // 5. Asignar las entidades relacionadas actualizadas
        userEntity.setTipoRol(rol);
        userEntity.setOficio(oficio);
        userEntity.setNivelAcademico(nivel);
        userEntity.setArea(area);
        userEntity.setPuesto(puesto);

        // 6. Guardar y devolver el resultado
        UserEntity updatedEmpleado = repUser.save(userEntity);

        return IUserMapper.INSTANCE.userEntityToUserResponseDTO(updatedEmpleado);
    }

    // deactivate
    @Transactional
    public UserResponseDTO desactivateSer(Long id) {
        UserEntity empleado = repUser.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El empleado con ID " + id + " no fue encontrado"));
        if (!empleado.isStatus()) {
            throw new InvalidStateException("El usuario ya está desactivado");
        }
        empleado.setStatus(false);
        UserEntity savedEmpleado = repUser.save(empleado);
        return IUserMapper.INSTANCE.userEntityToUserResponseDTO(savedEmpleado);
    }

    // activate
    @Transactional
    public UserResponseDTO activateSer(Long id) {
        UserEntity empleado = repUser.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El empleado con ID " + id + " no fue encontrado"));
        if (empleado.isStatus()) {
            throw new InvalidStateException("El usuario ya está activado");
        }
        empleado.setStatus(true);
        UserEntity savedEmpleado = repUser.save(empleado);
        return IUserMapper.INSTANCE.userEntityToUserResponseDTO(savedEmpleado);
    }

    private void validateUniqueFields(String telefono, String numeroDocumento, String email) {
        repUser.findByTelefono(telefono).ifPresent(e -> {
            throw new ResourceAlreadyExistsException("El teléfono '" + telefono + "' ya está registrado.");
        });
        repUser.findByNumeroDocumento(numeroDocumento).ifPresent(e -> {
            throw new ResourceAlreadyExistsException("El número de documento '" + numeroDocumento + "' ya está registrado.");
        });
        repUser.findByEmail(email).ifPresent(e -> {
            throw new ResourceAlreadyExistsException("El email '" + email + "' ya está registrado.");
        });
    }
}

