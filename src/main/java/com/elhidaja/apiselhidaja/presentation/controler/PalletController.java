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

import com.elhidaja.apiselhidaja.presentation.dto.pallet.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.pallet.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.PalletService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pallets")
@Validated
public class PalletController {
      @Autowired
    private PalletService palletService;

    @PostMapping("/getall")
    public ResponseEntity<ResponsePalletAllDTO> getPallets(
            @Valid @RequestBody RequestPalletOptionDTO option) {
        return ResponseEntity.ok(palletService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetallePalletDTO> getByIdPallet(
            @Valid @RequestBody RequestPalletIdDTO id) {
        return ResponseEntity.ok(palletService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponserPalletMensajeDTO> insertarPallet(
            @Valid @RequestBody RequestPalletInsertDTO dto) {
        return ResponseEntity.ok(palletService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponserPalletMensajeDTO> actualizarPallet(
            @Valid @RequestBody RequestPalletUpdateDTO dto) {
        return ResponseEntity.ok(palletService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponserPalletMensajeDTO> activarPallet(
            @Valid @RequestBody RequestPalletIdDTO id) {
        return ResponseEntity.ok(palletService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponserPalletMensajeDTO> desactivarPallet(
            @Valid @RequestBody RequestPalletIdDTO id) {
        return ResponseEntity.ok(palletService.desactivateSer(id));
    }
}
