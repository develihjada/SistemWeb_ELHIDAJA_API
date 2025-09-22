package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.persistence.repository.AlmacenRepository;
import com.elhidaja.apiselhidaja.presentation.dto.almacen.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.almacen.Response.*;

@Service
@Validated
public class AlmacenService {
        private final AlmacenRepository almacenRepo;

    public AlmacenService(AlmacenRepository almacenRepo) {
        this.almacenRepo = almacenRepo;
    }

    @Transactional
    public ResponseAlmacenMensajeDTO insertSer(RequestAlmacenInsertDTO objAlmacen) {
        return almacenRepo.insertD(objAlmacen);
    }

    @Transactional
    public ResponseAlmacenMensajeDTO updateSer(RequestAlmacenUpdateDTO objAlmacen) {
        return almacenRepo.updateD(objAlmacen);
    }

    @Transactional
    public ResponseAlmacenAllDTO getAllSer(RequestAlmacenOptionDTO option) {
        return almacenRepo.getAllD(option);
    }

    @Transactional
    public ResponseDetalleAlmacenDTO getByIdSer(RequestAlmacenIdDTO id) {
        return almacenRepo.getByIdD(id);
    }

    @Transactional
    public ResponseAlmacenMensajeDTO activateSer(RequestAlmacenIdDTO id) {
        return almacenRepo.activateD(id);
    }

    @Transactional
    public ResponseAlmacenMensajeDTO desactivateSer(RequestAlmacenIdDTO id) {
        return almacenRepo.desactivateD(id);
    }
}
