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

import com.elhidaja.apiselhidaja.presentation.dto.ubicacion.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.ubicacion.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.UbicacionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ubicaciones")
@Validated
public class UbicacionController {
      @Autowired
    private UbicacionService ubicacionService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseUbicacionAllDTO> getUbicaciones(
            @Valid @RequestBody RequestUbicacionOptionDTO option) {
        return ResponseEntity.ok(ubicacionService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleUbicacionDTO> getByIdUbicacion(
            @Valid @RequestBody RequestUbicacionIdDTO id) {
        return ResponseEntity.ok(ubicacionService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponserUbicacionMensajeDTO> insertarUbicacion(
            @Valid @RequestBody RequestUbicacionInsertDTO dto) {
        return ResponseEntity.ok(ubicacionService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponserUbicacionMensajeDTO> actualizarUbicacion(
            @Valid @RequestBody RequestUbicacionUpdateDTO dto) {
        return ResponseEntity.ok(ubicacionService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponserUbicacionMensajeDTO> activarUbicacion(
            @Valid @RequestBody RequestUbicacionIdDTO id) {
        return ResponseEntity.ok(ubicacionService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponserUbicacionMensajeDTO> desactivarUbicacion(
            @Valid @RequestBody RequestUbicacionIdDTO id) {
        return ResponseEntity.ok(ubicacionService.desactivateSer(id));
    }
}
