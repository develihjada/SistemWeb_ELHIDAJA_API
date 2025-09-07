package com.hidajasac.hidajasac_apis.service.implementacion.rolImp;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.rol.RolEntity;
import com.hidajasac.hidajasac_apis.persistence.repository.rolRep.IRol;
import com.hidajasac.hidajasac_apis.persistence.repository.userRep.IUser;
import com.hidajasac.hidajasac_apis.presentation.dto.rolD.RolCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.rolD.RolResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.rolD.RolUpdateDTO;
import com.hidajasac.hidajasac_apis.service.exeption.InvalidStateException;
import com.hidajasac.hidajasac_apis.service.exeption.ResourceAlreadyExistsException;
import com.hidajasac.hidajasac_apis.util.mapper.rolMap.IRolMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RolImp {

    private final IRol rolRepository;
    private final IUser userRepository;

    public RolImp(IRol rolRepository, IUser userRepository) {
        this.rolRepository = rolRepository;
        this.userRepository = userRepository;
    }

    // Obtener todos los roles
    public List<RolResponseDTO> getAllS() {
        Iterable<RolEntity> roles = rolRepository.findAll();
        List<RolResponseDTO> responseList = new ArrayList<>();
        for (RolEntity r : roles) {
            responseList.add(IRolMapper.INSTANCE.rolEntityToRolResponseDTO(r));
        }
        return responseList;
    }

    // Obtener por ID
    public RolResponseDTO getByIdS(Long id) {
        RolEntity rol = rolRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El rol con ID " + id + " no fue encontrado"));
        return IRolMapper.INSTANCE.rolEntityToRolResponseDTO(rol);
    }

    // Crear nuevo rol
    public RolResponseDTO saveSer(RolCreateDTO dto) {
        Optional<RolEntity> existing = rolRepository.findByNombre(dto.getNombre());
        if (existing.isPresent()) {
            throw new ResourceAlreadyExistsException("El rol con tipo '" + dto.getNombre() + "' ya existe");
        }
        RolEntity saved = rolRepository.save(IRolMapper.INSTANCE.rolCreateDTOToRolEntity(dto));
        return IRolMapper.INSTANCE.rolEntityToRolResponseDTO(saved);
    }

    // Actualizar rol
    public RolResponseDTO updateSer(RolUpdateDTO dto, Long id) {
        RolEntity rol = rolRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El rol con ID " + id + " no fue encontrado"));
        // Validar si el tipo de rol existe y no es el mismo registro
        Optional<RolEntity> existing = rolRepository.findByNombre(dto.getNombre());
        if (existing.isPresent() && !existing.get().getId().equals(id)) {
            throw new ResourceAlreadyExistsException("El rol con tipo '" + dto.getNombre() + "' ya existe");
        }

        IRolMapper.INSTANCE.updateRolFromDto(dto, rol);
        RolEntity updated = rolRepository.save(rol);
        return IRolMapper.INSTANCE.rolEntityToRolResponseDTO(updated);
    }

    // Desactivar rol
    public RolResponseDTO desactivateSer(Long id) {
        RolEntity rol = rolRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El rol con ID " + id + " no fue encontrado"));

        if (!rol.isStatus()) {
            throw new InvalidStateException("El rol ya está desactivado");
        }
        if (userRepository.existsByRolIdAndStatusTrue(id)) {
            throw new InvalidStateException("No se puede desactivar el rol ya que contiene usuarios activos");
        }
        rol.setStatus(false);
        RolEntity updated = rolRepository.save(rol);
        return IRolMapper.INSTANCE.rolEntityToRolResponseDTO(updated);
    }

    // Activar rol
    public RolResponseDTO activateSer(Long id) {
        RolEntity rol = rolRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El rol con ID " + id + " no fue encontrado"));

        if (rol.isStatus()) {
            throw new InvalidStateException("El rol ya está activado");
        }

        rol.setStatus(true);
        RolEntity updated = rolRepository.save(rol);
        return IRolMapper.INSTANCE.rolEntityToRolResponseDTO(updated);
    }
}