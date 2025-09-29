package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.persistence.repository.EstanteRepository;
import com.elhidaja.apiselhidaja.presentation.dto.estante.Request.RequestEstanteIdDTO;
import com.elhidaja.apiselhidaja.presentation.dto.estante.Request.RequestEstanteInsertDTO;
import com.elhidaja.apiselhidaja.presentation.dto.estante.Request.RequestEstanteOptionDTO;
import com.elhidaja.apiselhidaja.presentation.dto.estante.Request.RequestEstanteUpdateDTO;
import com.elhidaja.apiselhidaja.presentation.dto.estante.Response.ResponseDetalleEstanteDTO;
import com.elhidaja.apiselhidaja.presentation.dto.estante.Response.ResponseEstanteAllDTO;
import com.elhidaja.apiselhidaja.presentation.dto.estante.Response.ResponseEstanteMensajeDTO;

@Service
@Validated
public class EstanteService {
     private final EstanteRepository estanteRepo;

    public EstanteService(EstanteRepository estanteRepo) {
        this.estanteRepo = estanteRepo;
    }

    @Transactional
    public ResponseEstanteMensajeDTO insertSer(RequestEstanteInsertDTO objEstante) {
        return estanteRepo.insertD(objEstante);
    }

    @Transactional
    public ResponseEstanteMensajeDTO updateSer(RequestEstanteUpdateDTO objEstante) {
        return estanteRepo.updateD(objEstante);
    }

    @Transactional
    public ResponseEstanteAllDTO getAllSer(RequestEstanteOptionDTO option) {
        return estanteRepo.getAllD(option);
    }

    @Transactional
    public ResponseDetalleEstanteDTO getByIdSer(RequestEstanteIdDTO id) {
        return estanteRepo.getByIdD(id);
    }

    @Transactional
    public ResponseEstanteMensajeDTO activateSer(RequestEstanteIdDTO id) {
        return estanteRepo.activateD(id);
    }

    @Transactional
    public ResponseEstanteMensajeDTO desactivateSer(RequestEstanteIdDTO id) {
        return estanteRepo.desactivateD(id);
    }
}
