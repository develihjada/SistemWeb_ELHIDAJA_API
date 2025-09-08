package com.hidajasac.hidajasac_apis.service.implementacion.nivelAcademicoImp;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.nivelAcademico.NivelAcademicoEntity;
import com.hidajasac.hidajasac_apis.persistence.repository.nivelAcademicoRep.INivelAcademico;
import com.hidajasac.hidajasac_apis.persistence.repository.userRep.IUser;
import com.hidajasac.hidajasac_apis.presentation.dto.nivelAcademicoD.NivelAcademicoCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.nivelAcademicoD.NivelAcademicoResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.nivelAcademicoD.NivelAcademicoUpdateDTO;
import com.hidajasac.hidajasac_apis.service.exeption.InvalidStateException;
import com.hidajasac.hidajasac_apis.service.exeption.ResourceAlreadyExistsException;
import com.hidajasac.hidajasac_apis.util.mapper.INivelAcademicoMap.INivelAcademicoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NivelAcademicoImp {

    private final INivelAcademico repNivel;
    private final IUser userRepository;

    public NivelAcademicoImp(INivelAcademico repNivel, IUser userRepository) {
        this.repNivel = repNivel;
        this.userRepository = userRepository;
    }

    //findAll
    public List<NivelAcademicoResponseDTO> getAllS() {
        Iterable<NivelAcademicoEntity> itNivel = repNivel.findAll();
        List<NivelAcademicoResponseDTO> list = new ArrayList<>();
        for (NivelAcademicoEntity e : itNivel) {
            list.add(INivelAcademicoMapper.INSTANCE.nivelEntityToResponseDTO(e));
        }
        return list;
    }

    //findById
    public NivelAcademicoResponseDTO getByIdS(Long id) {
        NivelAcademicoEntity e = repNivel.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El nombre académico con ID " + id + " no fue encontrado"));
        return INivelAcademicoMapper.INSTANCE.nivelEntityToResponseDTO(e);
    }

    //create
    public NivelAcademicoResponseDTO saveSer(NivelAcademicoCreateDTO dto) {
        Optional<NivelAcademicoEntity> existing = repNivel.findByNombre(dto.getNombre());
        if (existing.isPresent()) {
            throw new ResourceAlreadyExistsException("El nombre académico '" + dto.getNombre() + "' ya existe");
        }
        NivelAcademicoEntity saved = repNivel.save(INivelAcademicoMapper.INSTANCE.createDTOToEntity(dto));
        return INivelAcademicoMapper.INSTANCE.nivelEntityToResponseDTO(saved);
    }

    //update
    public NivelAcademicoResponseDTO updateSer(NivelAcademicoUpdateDTO dto, Long id) {
        NivelAcademicoEntity e = repNivel.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El nombre académico con ID " + id + " no fue encontrado"));
        // Validar que no exista otro registro con el mismo nombre
        Optional<NivelAcademicoEntity> existing = repNivel.findByNombre(dto.getNombre());
        if (existing.isPresent() && !existing.get().getId().equals(id)) {
            throw new ResourceAlreadyExistsException("El nombre académico '" + dto.getNombre() + "' ya existe");
        }

        INivelAcademicoMapper.INSTANCE.updateFromDto(dto, e);
        NivelAcademicoEntity saved = repNivel.save(e);
        return INivelAcademicoMapper.INSTANCE.nivelEntityToResponseDTO(saved);
    }

    //deactivate
    public NivelAcademicoResponseDTO desactivateSer(Long id) {
        NivelAcademicoEntity e = repNivel.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El nombre académico con ID " + id + " no fue encontrado"));

        if (!e.isStatus()) {
            throw new InvalidStateException("El nombre académico ya está desactivado");
        }

        if (userRepository.existsByNivelAcademicoIdAndStatusTrue(id)){
            throw new InvalidStateException("No se puede desactivar el nombre academico por que contiene  usuarios activos");
        }

        e.setStatus(false);
        NivelAcademicoEntity saved = repNivel.save(e);
        return INivelAcademicoMapper.INSTANCE.nivelEntityToResponseDTO(saved);
    }

    //activate
    public NivelAcademicoResponseDTO activateSer(Long id) {
        NivelAcademicoEntity e = repNivel.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El nombre académico con ID " + id + " no fue encontrado"));

        if (e.isStatus()) {
            throw new InvalidStateException("El nombre académico ya está activado");
        }

        e.setStatus(true);
        NivelAcademicoEntity saved = repNivel.save(e);
        return INivelAcademicoMapper.INSTANCE.nivelEntityToResponseDTO(saved);
    }
}
