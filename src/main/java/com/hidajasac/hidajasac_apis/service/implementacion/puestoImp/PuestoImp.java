package com.hidajasac.hidajasac_apis.service.implementacion.puestoImp;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.puesto.PuestoEntity;
import com.hidajasac.hidajasac_apis.persistence.repository.puestoRep.IPuesto;
import com.hidajasac.hidajasac_apis.presentation.dto.puestoD.PuestoCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.puestoD.PuestoResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.puestoD.PuestoUpdateDTO;
import com.hidajasac.hidajasac_apis.service.exeption.InvalidStateException;
import com.hidajasac.hidajasac_apis.service.exeption.ResourceAlreadyExistsException;
import com.hidajasac.hidajasac_apis.util.mapper.puestoMap.IPuestoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PuestoImp {
    private final IPuesto repPuesto;

    public PuestoImp(IPuesto repPuesto) {
        this.repPuesto = repPuesto;
    }

    //find
    public List<PuestoResponseDTO> getAllS() {
        Iterable<PuestoEntity> itPuesto = repPuesto.findAll();
        List<PuestoResponseDTO> list = new ArrayList<>();
        for (PuestoEntity e : itPuesto) {
            list.add(IPuestoMapper.INSTANCE.puestoEntityToPuestoResponseDTO(e));
        }
        return list;
    }

    //find_by_id
    public PuestoResponseDTO getByIdS(Long id) {
        PuestoEntity p = repPuesto.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El puesto con ID " + id + " no fue encontrado"));
        return IPuestoMapper.INSTANCE.puestoEntityToPuestoResponseDTO(p);
    }

    //create
    public PuestoResponseDTO saveSer(PuestoCreateDTO dto) {
        Optional<PuestoEntity> exists = repPuesto.findByNombre(dto.getNombre());
        if (exists.isPresent()) {
            throw new ResourceAlreadyExistsException("El puesto con el nombre '" + dto.getNombre() + "' ya existe");
        }
        PuestoEntity saved = repPuesto.save(IPuestoMapper.INSTANCE.puestoCreateDTOToPuestoEntity(dto));
        return IPuestoMapper.INSTANCE.puestoEntityToPuestoResponseDTO(saved);
    }

    //update
    public PuestoResponseDTO updateSer(PuestoUpdateDTO dto, Long id) {
        PuestoEntity p = repPuesto.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El puesto con ID " + id + " no fue encontrado"));

        // Verificar si el nombre ya existe y no es el mismo registro
        Optional<PuestoEntity> existing = repPuesto.findByNombre(dto.getNombre());
        if (existing.isPresent() && !existing.get().getId().equals(id)) {
            throw new ResourceAlreadyExistsException("El puesto con el nombre '" + dto.getNombre() + "' ya existe");
        }
        //update
        IPuestoMapper.INSTANCE.updatePuestoFromDto(dto, p);
        PuestoEntity saved = repPuesto.save(p);
        return IPuestoMapper.INSTANCE.puestoEntityToPuestoResponseDTO(saved);
    }

    //deactivate
    public PuestoResponseDTO deactivateSer(Long id) {
        PuestoEntity p = repPuesto.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El puesto con ID " + id + " no fue encontrado"));

        if (!p.isStatus()) {  // Ya está desactivado
            throw new InvalidStateException("El puesto ya está desactivado");
        }

        p.setStatus(false);
        PuestoEntity saved = repPuesto.save(p);
        return IPuestoMapper.INSTANCE.puestoEntityToPuestoResponseDTO(saved);
    }

    //activate
    public PuestoResponseDTO activateSer(Long id) {
        PuestoEntity p = repPuesto.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El puesto con ID " + id + " no fue encontrado"));

        if (p.isStatus()) {  // Ya está activado
            throw new InvalidStateException("El puesto ya está activado");
        }

        p.setStatus(true);
        PuestoEntity saved = repPuesto.save(p);
        return IPuestoMapper.INSTANCE.puestoEntityToPuestoResponseDTO(saved);
    }
}