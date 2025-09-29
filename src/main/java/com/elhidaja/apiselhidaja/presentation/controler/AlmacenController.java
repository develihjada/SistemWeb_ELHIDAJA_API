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
import com.elhidaja.apiselhidaja.presentation.dto.almacen.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.almacen.Response.*;

import com.elhidaja.apiselhidaja.service.implementation.AlmacenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/almacenes")
@Validated
public class AlmacenController {
    
    @Autowired
    private AlmacenService almacenService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseAlmacenAllDTO> getAlmacenes(
            @Valid @RequestBody RequestAlmacenOptionDTO option) {
        return ResponseEntity.ok(almacenService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleAlmacenDTO> getByIdAlmacen(
            @Valid @RequestBody RequestAlmacenIdDTO id) {
        return ResponseEntity.ok(almacenService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseAlmacenMensajeDTO> insertarAlmacen(
            @Valid @RequestBody RequestAlmacenInsertDTO dto) {
        return ResponseEntity.ok(almacenService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseAlmacenMensajeDTO> actualizarAlmacen(
            @Valid @RequestBody RequestAlmacenUpdateDTO dto) {
        return ResponseEntity.ok(almacenService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseAlmacenMensajeDTO> activarAlmacen(
            @Valid @RequestBody RequestAlmacenIdDTO id) {
        return ResponseEntity.ok(almacenService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseAlmacenMensajeDTO> desactivarAlmacen(
            @Valid @RequestBody RequestAlmacenIdDTO id) {
        return ResponseEntity.ok(almacenService.desactivateSer(id));
    }
}
