package com.hidajasac.hidajasac_apis.service.implementacion.documentoIdentidad.Nacionalidad;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.Nacionalidad.TipoNacionalidadEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.tipoDocumento.TipoDocumentoIdentidadEntity;
import com.hidajasac.hidajasac_apis.persistence.repository.documentoIdentidad.Nacionalidad.ITipoNacionalidad;
import com.hidajasac.hidajasac_apis.persistence.repository.documentoIdentidad.tipoDocumento.ITipoDocumentoIdentidad;
import com.hidajasac.hidajasac_apis.persistence.repository.usuarioRep.IUser;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD.TipoNacionalidadCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD.TipoNacionalidadResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD.TipoNacionalidadUpdateDTO;
import com.hidajasac.hidajasac_apis.service.exeption.InvalidStateException;
import com.hidajasac.hidajasac_apis.service.exeption.ResourceAlreadyExistsException;
import com.hidajasac.hidajasac_apis.util.mapper.documentoIdentidad.NacionalidadMap.ITipoNacionalidadMapper;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TipoNacionalidadImp {
    private final ITipoNacionalidad repository;
    private final ITipoDocumentoIdentidad documentoRepo;
    private final IUser userRepository;

    public TipoNacionalidadImp(ITipoNacionalidad repository, ITipoDocumentoIdentidad documentoRepo, IUser userRepository) {
        this.repository = repository;
        this.documentoRepo = documentoRepo;
        this.userRepository = userRepository;
    }

    //findAll
    public List<TipoNacionalidadResponseDTO> getAll() {
        List<TipoNacionalidadResponseDTO> response = new ArrayList<>();
        for (TipoNacionalidadEntity entity : repository.findAll()) {
            response.add(ITipoNacionalidadMapper.INSTANCE.nacionalidadEntityToResponseDTO(entity));
        }
        return response;
    }

    //findById
    public TipoNacionalidadResponseDTO getById(Long id) {
        TipoNacionalidadEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La nacionalidad con ID " + id + " no fue encontrada"));
        return ITipoNacionalidadMapper.INSTANCE.nacionalidadEntityToResponseDTO(entity);
    }

    //create
    public TipoNacionalidadResponseDTO save(TipoNacionalidadCreateDTO dto) {
        Optional<TipoNacionalidadEntity> exists = repository.findByTipoNacionalidad(dto.getTipoNacionalidad());
        if (exists.isPresent()) {
            throw new ResourceAlreadyExistsException("La nacionalidad con nombre '" + dto.getTipoNacionalidad() + "' ya existe");
        }
        TipoNacionalidadEntity saved = repository.save(ITipoNacionalidadMapper.INSTANCE.createDTOToEntity(dto));
        return ITipoNacionalidadMapper.INSTANCE.nacionalidadEntityToResponseDTO(saved);
    }

    //update
    public TipoNacionalidadResponseDTO update(Long id, TipoNacionalidadUpdateDTO dto) {
        TipoNacionalidadEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La nacionalidad con ID " + id + " no fue encontrada"));

        // Verificar si el nombre ya existe en otro registro distinto
        Optional<TipoNacionalidadEntity> existing = repository.findByTipoNacionalidad(dto.getTipoNacionalidad());
        if (existing.isPresent() && !existing.get().getId().equals(id)) {
            throw new ResourceAlreadyExistsException("La nacionalidad con nombre '" + dto.getTipoNacionalidad() + "' ya existe");
        }

        ITipoNacionalidadMapper.INSTANCE.updateEntityFromDTO(dto, entity);
        TipoNacionalidadEntity updated = repository.save(entity);
        return ITipoNacionalidadMapper.INSTANCE.nacionalidadEntityToResponseDTO(updated);
    }

    //desactivate
    public TipoNacionalidadResponseDTO deactivate(Long id) {
        TipoNacionalidadEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La nacionalidad con ID " + id + " no fue encontrada"));

        if (!entity.isStatus()) {  // ya está desactivada
            throw new InvalidStateException("La nacionalidad ya está desactivada");
        }
        // Verificar si hay usuarios con esta nacionalidad activa
        if (userRepository.existsByTiponacionalidadIdAndStatusTrue(id)) {
            throw new InvalidStateException("No se puede desactivar la nacionalidad porque hay usuarios activos asociados a ella");
        }
        // Buscar documentos asociados a esta nacionalidad
        List<TipoDocumentoIdentidadEntity> documentos = documentoRepo.findByNacionalidadId(id);
        for (TipoDocumentoIdentidadEntity doc : documentos) {
            if (userRepository.existsByTipoDocumentoIdAndStatusTrue(doc.getId())) {
                throw new InvalidStateException("No se puede desactivar la nacionalidad porque hay documentos que tienen usuarios activos asociados a ella");
            }
            if (doc.isStatus()) {
                doc.setStatus(false);
                documentoRepo.save(doc);
            }
        }

        entity.setStatus(false);
        return ITipoNacionalidadMapper.INSTANCE.nacionalidadEntityToResponseDTO(repository.save(entity));
    }

    //activate
    public TipoNacionalidadResponseDTO activate(Long id) {
        TipoNacionalidadEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La nacionalidad con ID " + id + " no fue encontrada"));

        if (entity.isStatus()) {  // ya está activada
            throw new InvalidStateException("La nacionalidad ya está activada");
        }
        // Buscar documentos asociados a esta nacionalidad
        List<TipoDocumentoIdentidadEntity> documentos = documentoRepo.findByNacionalidadId(id);
        for (TipoDocumentoIdentidadEntity doc : documentos) {
            // Solo activamos documentos que no han sido desactivados manualmente
            if (!doc.isDesactivadoManualmente()){
                doc.setStatus(true);
                documentoRepo.save(doc);
            }
        }
        entity.setStatus(true);
        return ITipoNacionalidadMapper.INSTANCE.nacionalidadEntityToResponseDTO(repository.save(entity));
    }
}
