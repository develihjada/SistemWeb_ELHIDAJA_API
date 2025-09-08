package com.hidajasac.hidajasac_apis.service.implementacion.documentoIdentidad.NacionalidadImp;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.Documento.DocumentoIdentidadEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.Nacionalidad.NacionalidadEntity;
import com.hidajasac.hidajasac_apis.persistence.repository.documentoIdentidad.NacionalidadRep.INacionalidad;
import com.hidajasac.hidajasac_apis.persistence.repository.documentoIdentidad.DocumentoRep.IDocumentoIdentidad;
import com.hidajasac.hidajasac_apis.persistence.repository.userRep.IUser;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD.NacionalidadCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD.NacionalidadResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD.NacionalidadUpdateDTO;
import com.hidajasac.hidajasac_apis.service.exeption.InvalidStateException;
import com.hidajasac.hidajasac_apis.service.exeption.ResourceAlreadyExistsException;
import com.hidajasac.hidajasac_apis.util.mapper.documentoIdentidad.NacionalidadMap.INacionalidadMapper;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NacionalidadImp {
    private final INacionalidad repository;
    private final IDocumentoIdentidad documentoRepo;
    private final IUser userRepository;

    public NacionalidadImp(INacionalidad repository, IDocumentoIdentidad documentoRepo, IUser userRepository) {
        this.repository = repository;
        this.documentoRepo = documentoRepo;
        this.userRepository = userRepository;
    }

    //findAll
    public List<NacionalidadResponseDTO> getAll() {
        List<NacionalidadResponseDTO> response = new ArrayList<>();
        for (NacionalidadEntity entity : repository.findAll()) {
            response.add(INacionalidadMapper.INSTANCE.nacionalidadEntityToResponseDTO(entity));
        }
        return response;
    }

    //findById
    public NacionalidadResponseDTO getById(Long id) {
        NacionalidadEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La nacionalidad con ID " + id + " no fue encontrada"));
        return INacionalidadMapper.INSTANCE.nacionalidadEntityToResponseDTO(entity);
    }

    //create
    public NacionalidadResponseDTO save(NacionalidadCreateDTO dto) {
        Optional<NacionalidadEntity> exists = repository.findByNombre(dto.getNombre());
        if (exists.isPresent()) {
            throw new ResourceAlreadyExistsException("La nacionalidad con nombre '" + dto.getNombre() + "' ya existe");
        }
        NacionalidadEntity saved = repository.save(INacionalidadMapper.INSTANCE.createDTOToEntity(dto));
        return INacionalidadMapper.INSTANCE.nacionalidadEntityToResponseDTO(saved);
    }

    //update
    public NacionalidadResponseDTO update(Long id, NacionalidadUpdateDTO dto) {
        NacionalidadEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La nacionalidad con ID " + id + " no fue encontrada"));

        // Verificar si el nombre ya existe en otro registro distinto
        Optional<NacionalidadEntity> existing = repository.findByNombre(dto.getNombre());
        if (existing.isPresent() && !existing.get().getId().equals(id)) {
            throw new ResourceAlreadyExistsException("La nacionalidad con nombre '" + dto.getNombre() + "' ya existe");
        }

        INacionalidadMapper.INSTANCE.updateEntityFromDTO(dto, entity);
        NacionalidadEntity updated = repository.save(entity);
        return INacionalidadMapper.INSTANCE.nacionalidadEntityToResponseDTO(updated);
    }

    //desactivate
    public NacionalidadResponseDTO deactivate(Long id) {
        NacionalidadEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La nacionalidad con ID " + id + " no fue encontrada"));

        if (!entity.isStatus()) {  // ya está desactivada
            throw new InvalidStateException("La nacionalidad ya está desactivada");
        }
        // Verificar si hay usuarios con esta nacionalidad activa
        if (userRepository.existsByNacionalidadIdAndStatusTrue(id)) {
            throw new InvalidStateException("No se puede desactivar la nacionalidad porque hay usuarios activos asociados a ella");
        }
        // Buscar documentos asociados a esta nacionalidad
        List<DocumentoIdentidadEntity> documentos = documentoRepo.findByNacionalidadId(id);
        for (DocumentoIdentidadEntity doc : documentos) {
            if (userRepository.existsByDocumentoIdAndStatusTrue(doc.getId())) {
                throw new InvalidStateException("No se puede desactivar la nacionalidad porque hay documentos que tienen usuarios activos asociados a ella");
            }
            if (doc.isStatus()) {
                doc.setStatus(false);
                documentoRepo.save(doc);
            }
        }

        entity.setStatus(false);
        return INacionalidadMapper.INSTANCE.nacionalidadEntityToResponseDTO(repository.save(entity));
    }

    //activate
    public NacionalidadResponseDTO activate(Long id) {
        NacionalidadEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La nacionalidad con ID " + id + " no fue encontrada"));

        if (entity.isStatus()) {  // ya está activada
            throw new InvalidStateException("La nacionalidad ya está activada");
        }
        // Buscar documentos asociados a esta nacionalidad
        List<DocumentoIdentidadEntity> documentos = documentoRepo.findByNacionalidadId(id);
        for (DocumentoIdentidadEntity doc : documentos) {
            // Solo activamos documentos que no han sido desactivados manualmente
            if (!doc.isDesactivadoManualmente()){
                doc.setStatus(true);
                documentoRepo.save(doc);
            }
        }
        entity.setStatus(true);
        return INacionalidadMapper.INSTANCE.nacionalidadEntityToResponseDTO(repository.save(entity));
    }
}
