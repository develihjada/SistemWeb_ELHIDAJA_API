package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.persistence.repository.ProductoUnidadMedidaRepository;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedidaProducto.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedidaProducto.Response.*;

@Service
@Validated
public class ProductoUnidadMedidaService {
      private final ProductoUnidadMedidaRepository productoUnidadMedidaRepo;

    public ProductoUnidadMedidaService(ProductoUnidadMedidaRepository productoUnidadMedidaRepo) {
        this.productoUnidadMedidaRepo = productoUnidadMedidaRepo;
    }

    @Transactional
    public ResponserProductoUnidadMedidaMensajeDTO insertSer(RequestProductoUnidadMedidaInsertDTO obj) {
        return productoUnidadMedidaRepo.insertD(obj);
    }

    @Transactional
    public ResponserProductoUnidadMedidaMensajeDTO updateSer(RequestProductoUnidadMedidaUpdateDTO obj) {
        return productoUnidadMedidaRepo.updateD(obj);
    }

    @Transactional(readOnly = true)
    public ResponseProductoUnidadMedidaAllDTO getAllSer(RequestProductoUnidadMedidaOptionDTO option) {
        return productoUnidadMedidaRepo.getAllD(option);
    }

    @Transactional(readOnly = true)
    public ResponseDetalleProductoUnidadMedidaDTO getByIdSer(RequestProductoUnidadMedidaIdDTO id) {
        return productoUnidadMedidaRepo.getByIdD(id);
    }

    @Transactional
    public ResponserProductoUnidadMedidaMensajeDTO activateSer(RequestProductoUnidadMedidaIdDTO id) {
        return productoUnidadMedidaRepo.activateD(id);
    }

    @Transactional
    public ResponserProductoUnidadMedidaMensajeDTO desactivateSer(RequestProductoUnidadMedidaIdDTO id) {
        return productoUnidadMedidaRepo.desactivateD(id);
    }
}
