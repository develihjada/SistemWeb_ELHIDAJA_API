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

import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Response.ResponseDetalleUnidadMedidaDTO;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Response.ResponseUnidadMedidaAllDTO;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Response.ResponseUnidadMedidaMensajeDTO;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Resquest.RequestUnidadMedidaIdDTO;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Resquest.RequestUnidadMedidaInsertDTO;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Resquest.RequestUnidadMedidaOptionDTO;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Resquest.RequestUnidadMedidaUpdateDTO;
import com.elhidaja.apiselhidaja.service.implementation.UnidadMedidaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/unidades-medida")
@Validated
public class UnidadMedidaController {
    
    @Autowired
    private UnidadMedidaService unidadMedidaService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseUnidadMedidaAllDTO> getUnidadesMedida(
            @Valid @RequestBody RequestUnidadMedidaOptionDTO option) {
        return ResponseEntity.ok(unidadMedidaService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleUnidadMedidaDTO> getByIdUnidadMedida(
            @Valid @RequestBody RequestUnidadMedidaIdDTO id) {
        return ResponseEntity.ok(unidadMedidaService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseUnidadMedidaMensajeDTO> insertarUnidadMedida(
            @Valid @RequestBody RequestUnidadMedidaInsertDTO dto) {
        return ResponseEntity.ok(unidadMedidaService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseUnidadMedidaMensajeDTO> actualizarUnidadMedida(
            @Valid @RequestBody RequestUnidadMedidaUpdateDTO dto) {
        return ResponseEntity.ok(unidadMedidaService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseUnidadMedidaMensajeDTO> activarUnidadMedida(
            @Valid @RequestBody RequestUnidadMedidaIdDTO id) {
        return ResponseEntity.ok(unidadMedidaService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseUnidadMedidaMensajeDTO> desactivarUnidadMedida(
            @Valid @RequestBody RequestUnidadMedidaIdDTO id) {
        return ResponseEntity.ok(unidadMedidaService.desactivateSer(id));
    }
}
