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

import com.elhidaja.apiselhidaja.presentation.dto.estante.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.estante.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.EstanteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/estantes")
@Validated
public class EstanteController {
      @Autowired
    private EstanteService estanteService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseEstanteAllDTO> getEstantes(
            @Valid @RequestBody RequestEstanteOptionDTO option) {
        return ResponseEntity.ok(estanteService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleEstanteDTO> getByIdEstante(
            @Valid @RequestBody RequestEstanteIdDTO id) {
        return ResponseEntity.ok(estanteService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseEstanteMensajeDTO> insertarEstante(
            @Valid @RequestBody RequestEstanteInsertDTO dto) {
        return ResponseEntity.ok(estanteService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseEstanteMensajeDTO> actualizarEstante(
            @Valid @RequestBody RequestEstanteUpdateDTO dto) {
        return ResponseEntity.ok(estanteService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseEstanteMensajeDTO> activarEstante(
            @Valid @RequestBody RequestEstanteIdDTO id) {
        return ResponseEntity.ok(estanteService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseEstanteMensajeDTO> desactivarEstante(
            @Valid @RequestBody RequestEstanteIdDTO id) {
        return ResponseEntity.ok(estanteService.desactivateSer(id));
    }

}
