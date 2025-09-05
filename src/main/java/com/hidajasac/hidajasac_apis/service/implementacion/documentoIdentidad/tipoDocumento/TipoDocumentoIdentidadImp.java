package com.hidajasac.hidajasac_apis.service.implementacion.documentoIdentidad.tipoDocumento;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.Nacionalidad.TipoNacionalidadEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.tipoDocumento.TipoDocumentoIdentidadEntity;
import com.hidajasac.hidajasac_apis.persistence.repository.documentoIdentidad.Nacionalidad.ITipoNacionalidad;
import com.hidajasac.hidajasac_apis.persistence.repository.documentoIdentidad.tipoDocumento.ITipoDocumentoIdentidad;
import com.hidajasac.hidajasac_apis.persistence.repository.usuarioRep.IUser;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.tipoDocumentoD.TipoDocumentoIdentidadCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.tipoDocumentoD.TipoDocumentoIdentidadResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.tipoDocumentoD.TipoDocumentoIdentidadUpdateDTO;
import com.hidajasac.hidajasac_apis.service.exeption.InvalidStateException;
import com.hidajasac.hidajasac_apis.service.exeption.ResourceAlreadyExistsException;
import com.hidajasac.hidajasac_apis.service.implementacion.documentoIdentidad.Nacionalidad.TipoNacionalidadImp;
import com.hidajasac.hidajasac_apis.util.mapper.documentoIdentidad.tipoDocumentoMap.ITipoDocumentoIdentidadMapper;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public  class TipoDocumentoIdentidadImp {
    private final ITipoDocumentoIdentidad repository;
    private final ITipoNacionalidad repositoryNacionalidad;
    private final IUser userRepository;

    public TipoDocumentoIdentidadImp(ITipoDocumentoIdentidad repository, ITipoNacionalidad repositoryNacionalidad, IUser userRepository) {
        this.repository = repository;
        this.repositoryNacionalidad = repositoryNacionalidad;
        this.userRepository = userRepository;
    }

    //find all
    public List<TipoDocumentoIdentidadResponseDTO> getAll() {
        List<TipoDocumentoIdentidadResponseDTO> response = new ArrayList<>();
        for (TipoDocumentoIdentidadEntity entity : repository.findAll()) {
            response.add(ITipoDocumentoIdentidadMapper.INSTANCE.entityToResponseDTO(entity));
        }
        return response;
    }

    //find by ID
    public TipoDocumentoIdentidadResponseDTO getById(Long id) {
        TipoDocumentoIdentidadEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El tipo de documento con ID " + id + " no fue encontrado"));
        return ITipoDocumentoIdentidadMapper.INSTANCE.entityToResponseDTO(entity);
    }

    //create
    public TipoDocumentoIdentidadResponseDTO create(TipoDocumentoIdentidadCreateDTO dto) {
        Optional<TipoDocumentoIdentidadEntity> existing = repository.findByCodigo(dto.getCodigo());
        if (existing.isPresent()) {
            throw new ResourceAlreadyExistsException("El tipo de documento con código '" + dto.getCodigo() + "' ya existe");
        }
        // Buscar la nacionalidad por id
        TipoNacionalidadEntity nacionalidad = repositoryNacionalidad.findById(dto.getTipoNacionalidadId())
                .orElseThrow(() -> new EntityNotFoundException("Nacionalidad no encontrada"));

        //verificar si la nacionalidad esta activa
        if (!nacionalidad.isStatus()){
            throw new InvalidStateException("La nacionalidad está desactivada");
        }
        TipoDocumentoIdentidadEntity entity = repository.save(ITipoDocumentoIdentidadMapper.INSTANCE.createDTOToEntity(dto));
        return ITipoDocumentoIdentidadMapper.INSTANCE.entityToResponseDTO(entity);
    }

    //update
    public TipoDocumentoIdentidadResponseDTO update(Long id, TipoDocumentoIdentidadUpdateDTO dto) {
        TipoDocumentoIdentidadEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El tipo de documento con ID " + id + " no fue encontrado"));
        // Verificar si el nombre ya existe en otro registro distinto
        Optional<TipoDocumentoIdentidadEntity> existing = repository.findByCodigo(dto.getCodigo());
        if (existing.isPresent() && !existing.get().getId().equals(id)) {
            throw new ResourceAlreadyExistsException("El tipo de documento con nombre '" + dto.getCodigo() + "' ya existe");
        }

        ITipoDocumentoIdentidadMapper.INSTANCE.updateEntityFromDTO(dto, entity);
        return ITipoDocumentoIdentidadMapper.INSTANCE.entityToResponseDTO(repository.save(entity));
    }

    //deactivate
    public TipoDocumentoIdentidadResponseDTO deactivate(Long id) {
        TipoDocumentoIdentidadEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El tipo de documento con ID " + id + " no fue encontrado"));

        if (!entity.isStatus()) {  // ya está desactivada
            throw new InvalidStateException("el tipo de documento ya está desactivado");
        }

        if (userRepository.existsByTiponacionalidadIdAndStatusTrue(id)) {
            throw new InvalidStateException("No se puede desactivar el tipo de documento porque hay usuarios activos asociados a ella");
        }

        entity.setStatus(false);
        entity.setDesactivadoManualmente(true);
        return ITipoDocumentoIdentidadMapper.INSTANCE.entityToResponseDTO(repository.save(entity));
    }

    //activate
    public TipoDocumentoIdentidadResponseDTO activate(Long id) {
        TipoDocumentoIdentidadEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El tipo de documento con ID " + id + " no fue encontrado"));

        if (entity.isStatus()) {  // ya está desactivada
            throw new InvalidStateException("el tipo de documento ya está activado");
        }

        entity.setStatus(true);
        entity.setDesactivadoManualmente(false);
        return ITipoDocumentoIdentidadMapper.INSTANCE.entityToResponseDTO(repository.save(entity));
    }
}
