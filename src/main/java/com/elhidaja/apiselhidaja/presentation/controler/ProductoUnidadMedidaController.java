package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elhidaja.apiselhidaja.presentation.dto.unidadMedidaProducto.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedidaProducto.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.ProductoUnidadMedidaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/producto-unidad-medida")
@Validated
public class ProductoUnidadMedidaController {
       @Autowired
    private ProductoUnidadMedidaService productoUnidadMedidaService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseProductoUnidadMedidaAllDTO> getProductoUnidadMedidas(
            @Valid @RequestBody RequestProductoUnidadMedidaOptionDTO option) {
        return ResponseEntity.ok(productoUnidadMedidaService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleProductoUnidadMedidaDTO> getByIdProductoUnidadMedida(
            @Valid @RequestBody RequestProductoUnidadMedidaIdDTO id) {
        return ResponseEntity.ok(productoUnidadMedidaService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponserProductoUnidadMedidaMensajeDTO> insertarProductoUnidadMedida(
            @Valid @RequestBody RequestProductoUnidadMedidaInsertDTO dto) {
        return ResponseEntity.ok(productoUnidadMedidaService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponserProductoUnidadMedidaMensajeDTO> actualizarProductoUnidadMedida(
            @Valid @RequestBody RequestProductoUnidadMedidaUpdateDTO dto) {
        return ResponseEntity.ok(productoUnidadMedidaService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponserProductoUnidadMedidaMensajeDTO> activarProductoUnidadMedida(
            @Valid @RequestBody RequestProductoUnidadMedidaIdDTO id) {
        return ResponseEntity.ok(productoUnidadMedidaService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponserProductoUnidadMedidaMensajeDTO> desactivarProductoUnidadMedida(
            @Valid @RequestBody RequestProductoUnidadMedidaIdDTO id) {
        return ResponseEntity.ok(productoUnidadMedidaService.desactivateSer(id));
    }
}
