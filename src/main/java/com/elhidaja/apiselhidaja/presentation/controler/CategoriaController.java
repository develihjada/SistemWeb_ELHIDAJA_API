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

import com.elhidaja.apiselhidaja.presentation.dto.categoria.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.categoria.Resquest.*;
import com.elhidaja.apiselhidaja.service.implementation.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
@Validated
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseCategoriAllDTO> getCategorias(
            @Valid @RequestBody ResquestCategoriaOptionDTO option) {
        return ResponseEntity.ok(categoriaService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleCategoriaDTO> getByIdCategoria(
            @Valid @RequestBody RequesteCategoriaIdDTO id) {
        return ResponseEntity.ok(categoriaService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponserCategoriaMensajeDTO> insertarCategoria(
            @Valid @RequestBody RequestCategoriaInsertDTO dto) {
        return ResponseEntity.ok(categoriaService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponserCategoriaMensajeDTO> actualizarCategoria(
            @Valid @RequestBody RequestCategoriaUpdateDTO dto) {
        return ResponseEntity.ok(categoriaService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponserCategoriaMensajeDTO> activarCategoria(
            @Valid @RequestBody RequesteCategoriaIdDTO id) {
        return ResponseEntity.ok(categoriaService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponserCategoriaMensajeDTO> desactivarCategoria(
            @Valid @RequestBody RequesteCategoriaIdDTO id) {
        return ResponseEntity.ok(categoriaService.desactivateSer(id));
    }

}
