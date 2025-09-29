package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.persistence.repository.UbicacionRepository;
import com.elhidaja.apiselhidaja.presentation.dto.ubicacion.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.ubicacion.Response.*;
@Service
@Validated
public class UbicacionService {
     private final UbicacionRepository ubicacionRepo;

    public UbicacionService(UbicacionRepository ubicacionRepo) {
        this.ubicacionRepo = ubicacionRepo;
    }

    @Transactional
    public ResponserUbicacionMensajeDTO insertSer(RequestUbicacionInsertDTO objUbicacion) {
        return ubicacionRepo.insertD(objUbicacion);
    }

    @Transactional
    public ResponserUbicacionMensajeDTO updateSer(RequestUbicacionUpdateDTO objUbicacion) {
        return ubicacionRepo.updateD(objUbicacion);
    }

    @Transactional
    public ResponseUbicacionAllDTO getAllSer(RequestUbicacionOptionDTO option) {
        return ubicacionRepo.getAllD(option);
    }

    @Transactional
    public ResponseDetalleUbicacionDTO getByIdSer(RequestUbicacionIdDTO id) {
        return ubicacionRepo.getByIdD(id);
    }

    @Transactional
    public ResponserUbicacionMensajeDTO activateSer(RequestUbicacionIdDTO id) {
        return ubicacionRepo.activateD(id);
    }

    @Transactional
    public ResponserUbicacionMensajeDTO desactivateSer(RequestUbicacionIdDTO id) {
        return ubicacionRepo.desactivateD(id);
    }
}
