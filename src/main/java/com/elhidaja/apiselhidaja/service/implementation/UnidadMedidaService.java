package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhidaja.apiselhidaja.persistence.repository.UnidadMedidaRepository;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Response.ResponseDetalleUnidadMedidaDTO;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Response.ResponseUnidadMedidaAllDTO;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Response.ResponseUnidadMedidaMensajeDTO;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Resquest.RequestUnidadMedidaIdDTO;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Resquest.RequestUnidadMedidaInsertDTO;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Resquest.RequestUnidadMedidaOptionDTO;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Resquest.RequestUnidadMedidaUpdateDTO;

@Service
public class UnidadMedidaService {

    private final UnidadMedidaRepository unidadRepo;

    public UnidadMedidaService(UnidadMedidaRepository unidadRepo) {
        this.unidadRepo = unidadRepo;
    }

    @Transactional

    public ResponseUnidadMedidaMensajeDTO insertSer(RequestUnidadMedidaInsertDTO dto) {
        return unidadRepo.insertD(dto);
    }

    @Transactional

    public ResponseUnidadMedidaMensajeDTO updateSer(RequestUnidadMedidaUpdateDTO dto) {
        return unidadRepo.updateD(dto);
    }

    @Transactional
 
    public ResponseUnidadMedidaAllDTO getAllSer(RequestUnidadMedidaOptionDTO option) {
        return unidadRepo.getAllD(option);
    }

    @Transactional

    public ResponseDetalleUnidadMedidaDTO getByIdSer(RequestUnidadMedidaIdDTO id) {
        return unidadRepo.getByIdD(id);
    }

    @Transactional

    public ResponseUnidadMedidaMensajeDTO activateSer(RequestUnidadMedidaIdDTO id) {
        return unidadRepo.activateD(id);
    }

    @Transactional

    public ResponseUnidadMedidaMensajeDTO desactivateSer(RequestUnidadMedidaIdDTO id) {
        return unidadRepo.desactivateD(id);
    }
}
