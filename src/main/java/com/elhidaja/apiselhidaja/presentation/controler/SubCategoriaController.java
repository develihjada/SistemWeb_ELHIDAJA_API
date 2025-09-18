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

import com.elhidaja.apiselhidaja.presentation.dto.subCategoria.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.subCategoria.Resquest.*;
import com.elhidaja.apiselhidaja.service.implementation.SubCategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/subcategorias")
@Validated
public class SubCategoriaController {
     @Autowired
    private SubCategoriaService subCategoriaService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseSubCategoriAllDTO> getAll(
        
            @Valid @RequestBody ResquestSubCategoriaOptionDTO option) {
        return ResponseEntity.ok(subCategoriaService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleSubCategoriaDTO> getById(
            @Valid @RequestBody RequestSubCategoriaIdDTO id) {
        return ResponseEntity.ok(subCategoriaService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponserSubCategoriaMensajeDTO> create(
            @Valid @RequestBody RequestSubCategoriaInsertDTO dto) {
        return ResponseEntity.ok(subCategoriaService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponserSubCategoriaMensajeDTO> update(
            @Valid @RequestBody RequestSubCategoriaUpdateDTO dto) {
        return ResponseEntity.ok(subCategoriaService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponserSubCategoriaMensajeDTO> activate(
            @Valid @RequestBody RequestSubCategoriaIdDTO id) {
        return ResponseEntity.ok(subCategoriaService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponserSubCategoriaMensajeDTO> deactivate(
            @Valid @RequestBody RequestSubCategoriaIdDTO id) {
        return ResponseEntity.ok(subCategoriaService.desactivateSer(id));
    }
}
