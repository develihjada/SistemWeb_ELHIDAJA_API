package com.hidajasac.hidajasac_apis.service.implementacion.areacImp;

import com.hidajasac.hidajasac_apis.persistence.entity.area.AreaEntity;
import com.hidajasac.hidajasac_apis.persistence.repository.areaRep.IArea;
import com.hidajasac.hidajasac_apis.persistence.repository.usuarioRep.IUser;
import com.hidajasac.hidajasac_apis.presentation.dto.areaD.AreaCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.areaD.AreaResponeDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.areaD.AreaUpdateDTO;
import com.hidajasac.hidajasac_apis.service.exeption.InvalidStateException;
import com.hidajasac.hidajasac_apis.service.exeption.ResourceAlreadyExistsException;
import com.hidajasac.hidajasac_apis.util.mapper.areaMap.IAreaMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AreaImp {

    private final IArea repArea;
    private final IUser repuser;

    public AreaImp(IArea repArea, IUser repuser) {
        this.repArea = repArea;
        this.repuser = repuser;
    }

    //find
    public List<AreaResponeDTO> getAllS() {
        Iterable<AreaEntity> itAreaEntity = repArea.findAll();
        List<AreaResponeDTO> listAreaResponseDTO = new ArrayList<>();
        for (AreaEntity e : itAreaEntity) {
            listAreaResponseDTO.add(
                    IAreaMapper.INSTANCE.areaEntityToAreaResponseDTO(e)
            );
        }
        return listAreaResponseDTO;
    }

    //finById
    public AreaResponeDTO getByIdS(Long id) {
        AreaEntity aE = repArea.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El area con ID " + id + " no fue encontrado"));
        return IAreaMapper.INSTANCE.areaEntityToAreaResponseDTO(aE);
    }

    //create
    public AreaResponeDTO saveSer(AreaCreateDTO areaSave) {
        Optional<AreaEntity> existingArea = repArea.findByNombreArea(areaSave.getNombreArea());
        if (existingArea.isPresent()) {
            throw new ResourceAlreadyExistsException("El área con el nombre '" + areaSave.getNombreArea() + "' ya existe");
        }
        AreaEntity aE = repArea.save(IAreaMapper.INSTANCE.areaCreateDTOToAreaEntity(areaSave));
        return IAreaMapper.INSTANCE.areaEntityToAreaResponseDTO(aE);
    }

    //update
    public AreaResponeDTO updateSer(AreaUpdateDTO areaUp, Long id) {
        AreaEntity aE = repArea.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El area con ID " + id + " no fue encontrado"));

        //verificar si el nombre existe y no es el mismo registro
        Optional<AreaEntity> existingArea = repArea.findByNombreArea(areaUp.getNombreArea());
        if (existingArea.isPresent() && !existingArea.get().getId().equals(id)) {
            throw new ResourceAlreadyExistsException("El área con el nombre '" + areaUp.getNombreArea() + "' ya existe");
        }
        //update
        IAreaMapper.INSTANCE.updateAreaFromDto(areaUp, aE);
        AreaEntity areaSave = repArea.save(aE);
        return  IAreaMapper.INSTANCE.areaEntityToAreaResponseDTO(areaSave);
    }

    //desactivate
    public AreaResponeDTO desactivateSer(Long id) {
        AreaEntity area = repArea.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El área con ID " + id + " no fue encontrada"));

        if (!area.isStatus()) {  // verificar
            throw new InvalidStateException("El área ya está desactivada");
        }
        if (repuser.existsByAreaIdAndStatusTrue(id)){
            throw new InvalidStateException("No se puede desactivar el area por que tiene usuarios activos");
        }

        area.setStatus(false);
        AreaEntity areaSave = repArea.save(area);
        return  IAreaMapper.INSTANCE.areaEntityToAreaResponseDTO(areaSave);
    }
    //activate
    public  AreaResponeDTO activateSer(Long id ){
        AreaEntity area = repArea.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El área con ID " + id + " no fue encontrada"));

        if (area.isStatus()) {  //si ya esta activada
            throw new InvalidStateException("El área ya está activada");
        }

        area.setStatus(true);
        AreaEntity areaSave = repArea.save(area);
        return  IAreaMapper.INSTANCE.areaEntityToAreaResponseDTO(areaSave);
    }
}