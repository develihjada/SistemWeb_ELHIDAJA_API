package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.producto.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.producto.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/productos")
@Validated
public class ProductoController {
       @Autowired
    private ProductoService productoService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseProductoAllDTO> getProductos(
            @Valid @RequestBody RequestProductoOptionDTO option) {
        return ResponseEntity.ok(productoService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleProductoDTO> getProductoById(
            @Valid @RequestBody RequestProductoIdDTO id) {
        return ResponseEntity.ok(productoService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseProductoMensajeDTO> insertarProducto(
            @Valid @RequestBody RequestProductoInsertDTO dto) {
        return ResponseEntity.ok(productoService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseProductoMensajeDTO> actualizarProducto(
            @Valid @RequestBody RequestProductoUpdateDTO dto) {
        return ResponseEntity.ok(productoService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseProductoMensajeDTO> activarProducto(
            @Valid @RequestBody RequestProductoIdDTO id) {
        return ResponseEntity.ok(productoService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseProductoMensajeDTO> desactivarProducto(
            @Valid @RequestBody RequestProductoIdDTO id) {
        return ResponseEntity.ok(productoService.desactivateSer(id));
    }
}
