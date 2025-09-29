package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.persistence.repository.ProductoRepository;
import com.elhidaja.apiselhidaja.presentation.dto.producto.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.producto.Response.*;

@Service
@Validated
public class ProductoService {
     private final ProductoRepository productoRepo;

    public ProductoService(ProductoRepository productoRepo) {
        this.productoRepo = productoRepo;
    }

    @Transactional
    public ResponseProductoMensajeDTO insertSer(RequestProductoInsertDTO objProducto) {
        return productoRepo.insertD(objProducto);
    }

    @Transactional
    public ResponseProductoMensajeDTO updateSer(RequestProductoUpdateDTO objProducto) {
        return productoRepo.updateD(objProducto);
    }

    @Transactional(readOnly = true)
    public ResponseProductoAllDTO getAllSer(RequestProductoOptionDTO option) {
        return productoRepo.getAllD(option);
    }

    @Transactional(readOnly = true)
    public ResponseDetalleProductoDTO getByIdSer(RequestProductoIdDTO id) {
        return productoRepo.getByIdD(id);
    }

    @Transactional
    public ResponseProductoMensajeDTO activateSer(RequestProductoIdDTO id) {
        return productoRepo.activateD(id);
    }

    @Transactional
    public ResponseProductoMensajeDTO desactivateSer(RequestProductoIdDTO id) {
        return productoRepo.desactivateD(id);
    }
}
