package com.hidajasac.hidajasac_apis.service.implementacion.oficioImp;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.oficio.OficioEntity;
import com.hidajasac.hidajasac_apis.persistence.repository.oficioRep.IOficio;
import com.hidajasac.hidajasac_apis.persistence.repository.usuarioRep.IUser;
import com.hidajasac.hidajasac_apis.presentation.dto.oficioD.OficioCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.oficioD.OficioResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.oficioD.OficioUpdateDTO;
import com.hidajasac.hidajasac_apis.service.exeption.InvalidStateException;
import com.hidajasac.hidajasac_apis.service.exeption.ResourceAlreadyExistsException;
import com.hidajasac.hidajasac_apis.util.mapper.oficioMap.IOficioMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OficioImp {
    private final IOficio oficioRepository;
    private final IUser userRepository;

    public OficioImp(IOficio oficioRepository, IUser userRepository) {
        this.oficioRepository = oficioRepository;
        this.userRepository = userRepository;
    }

    //find
    public List<OficioResponseDTO> getAll() {
        Iterable<OficioEntity> itOficioEntity = oficioRepository.findAll();
        List<OficioResponseDTO> responseList = new ArrayList<>();
        for (OficioEntity e : itOficioEntity) {
            responseList.add(
                    IOficioMapper.INSTANCE.oficioEntityToResponseDTO(e)
            );
        }
        return responseList;
    }

    //findById
    public OficioResponseDTO getById(Long id) {
        OficioEntity entity = oficioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El oficio con ID " + id + " no fue encontrado"));
        return IOficioMapper.INSTANCE.oficioEntityToResponseDTO(entity);
    }

    //create
    public OficioResponseDTO create(OficioCreateDTO dto) {
        Optional<OficioEntity> existing = oficioRepository.findByTipoOficio(dto.getTipoOficio());
        if (existing.isPresent()) {
            throw new ResourceAlreadyExistsException("El oficio con el nombre '" + dto.getTipoOficio() + "' ya existe");
        }
        OficioEntity saved = oficioRepository.save(IOficioMapper.INSTANCE.oficioCreateDTOToEntity(dto));
        return IOficioMapper.INSTANCE.oficioEntityToResponseDTO(saved);
    }

    //update
    public OficioResponseDTO update(Long id, OficioUpdateDTO dto) {
        OficioEntity entity = oficioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El oficio con ID " + id + " no fue encontrado"));

        // Verificar si el tipoOficio ya existe
        Optional<OficioEntity> existing = oficioRepository.findByTipoOficio(dto.getTipoOficio());
        if (existing.isPresent() && !existing.get().getId().equals(id)) {
            throw new ResourceAlreadyExistsException("El oficio con el nombre '" + dto.getTipoOficio() + "' ya existe");
        }
        if (userRepository.existsByOficioIdAndStatusTrue(id)){
            throw new ResourceAlreadyExistsException("No se puede desactivar el oficio ya que contiene usuarios activos ");
        }
        //update
        IOficioMapper.INSTANCE.updateOficioFromDTO(dto, entity);
        return IOficioMapper.INSTANCE.oficioEntityToResponseDTO(oficioRepository.save(entity));
    }

    //deactivate
    public OficioResponseDTO deactivate(Long id) {
        OficioEntity entity = oficioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El oficio con ID " + id + " no fue encontrado"));

        if (!entity.isStatus()) {
            throw new InvalidStateException("El oficio ya está desactivado");
        }

        entity.setStatus(false);
        return IOficioMapper.INSTANCE.oficioEntityToResponseDTO(oficioRepository.save(entity));
    }

    //activate
    public OficioResponseDTO activate(Long id) {
        OficioEntity entity = oficioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El oficio con ID " + id + " no fue encontrado"));

        if (entity.isStatus()) {
            throw new InvalidStateException("El oficio ya está activado");
        }

        entity.setStatus(true);
        return IOficioMapper.INSTANCE.oficioEntityToResponseDTO(oficioRepository.save(entity));
    }
}
