package com.hidajasac.hidajasac_apis.service.implementacion.documentoIdentidad.DocumentoImp;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.Documento.DocumentoIdentidadEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.Nacionalidad.NacionalidadEntity;
import com.hidajasac.hidajasac_apis.persistence.repository.documentoIdentidad.NacionalidadRep.INacionalidad;
import com.hidajasac.hidajasac_apis.persistence.repository.documentoIdentidad.DocumentoRep.IDocumentoIdentidad;
import com.hidajasac.hidajasac_apis.persistence.repository.userRep.IUser;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.DocumentoD.DocumentoIdentidadCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.DocumentoD.DocumentoIdentidadResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.DocumentoD.DocumentoIdentidadUpdateDTO;
import com.hidajasac.hidajasac_apis.service.exeption.InvalidStateException;
import com.hidajasac.hidajasac_apis.service.exeption.ResourceAlreadyExistsException;
import com.hidajasac.hidajasac_apis.util.mapper.documentoIdentidad.DocumentoMap.IDocumentoIdentidadMapper;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public  class DocumentoIdentidadImp {
    private final IDocumentoIdentidad repository;
    private final INacionalidad repositoryNacionalidad;
    private final IUser userRepository;

    public DocumentoIdentidadImp(IDocumentoIdentidad repository, INacionalidad repositoryNacionalidad, IUser userRepository) {
        this.repository = repository;
        this.repositoryNacionalidad = repositoryNacionalidad;
        this.userRepository = userRepository;
    }

    //find all
    public List<DocumentoIdentidadResponseDTO> getAll() {
        List<DocumentoIdentidadResponseDTO> response = new ArrayList<>();
        for (DocumentoIdentidadEntity entity : repository.findAll()) {
            response.add(IDocumentoIdentidadMapper.INSTANCE.entityToResponseDTO(entity));
        }
        return response;
    }

    //find by ID
    public DocumentoIdentidadResponseDTO getById(Long id) {
        DocumentoIdentidadEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El documento con ID " + id + " no fue encontrado"));
        return IDocumentoIdentidadMapper.INSTANCE.entityToResponseDTO(entity);
    }

    //create
    public DocumentoIdentidadResponseDTO create(DocumentoIdentidadCreateDTO dto) {
        Optional<DocumentoIdentidadEntity> existing = repository.findByNombre(dto.getNombre());
        if (existing.isPresent()) {
            throw new ResourceAlreadyExistsException("El documento con el nombre " + dto.getNombre() + "' ya existe");
        }
        // Buscar la nacionalidad por id
        NacionalidadEntity nacionalidad = repositoryNacionalidad.findById(dto.getTipoNacionalidadId())
                .orElseThrow(() -> new EntityNotFoundException("NacionalidadRep no encontrada"));

        //verificar si la nacionalidad esta activa
        if (!nacionalidad.isStatus()){
            throw new InvalidStateException("La nacionalidad está desactivada");
        }
        DocumentoIdentidadEntity entity = repository.save(IDocumentoIdentidadMapper.INSTANCE.createDTOToEntity(dto));
        return IDocumentoIdentidadMapper.INSTANCE.entityToResponseDTO(entity);
    }

    //update
    public DocumentoIdentidadResponseDTO update(Long id, DocumentoIdentidadUpdateDTO dto) {
        DocumentoIdentidadEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El documento con ID " + id + " no fue encontrado"));
        // Verificar si el nombre ya existe en otro registro distinto
        Optional<DocumentoIdentidadEntity> existing = repository.findByNombre(dto.getNombre());
        if (existing.isPresent() && !existing.get().getId().equals(id)) {
            throw new ResourceAlreadyExistsException("El documento con nombre '" + dto.getNombre() + "' ya existe");
        }

        IDocumentoIdentidadMapper.INSTANCE.updateEntityFromDTO(dto, entity);
        return IDocumentoIdentidadMapper.INSTANCE.entityToResponseDTO(repository.save(entity));
    }

    //deactivate
    public DocumentoIdentidadResponseDTO deactivate(Long id) {
        DocumentoIdentidadEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El documento con ID " + id + " no fue encontrado"));

        if (!entity.isStatus()) {  // ya está desactivada
            throw new InvalidStateException("el  documento ya está desactivado");
        }

        if (userRepository.existsByNacionalidadIdAndStatusTrue(id)) {
            throw new InvalidStateException("No se puede desactivar el documento porque hay usuarios activos asociados a ella");
        }

        entity.setStatus(false);
        entity.setDesactivadoManualmente(true);
        return IDocumentoIdentidadMapper.INSTANCE.entityToResponseDTO(repository.save(entity));
    }

    //activate
    public DocumentoIdentidadResponseDTO activate(Long id) {
        DocumentoIdentidadEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El documento con ID " + id + " no fue encontrado"));

        if (entity.isStatus()) {  // ya está desactivada
            throw new InvalidStateException("el documento ya está activado");
        }

        entity.setStatus(true);
        entity.setDesactivadoManualmente(false);
        return IDocumentoIdentidadMapper.INSTANCE.entityToResponseDTO(repository.save(entity));
    }
}
