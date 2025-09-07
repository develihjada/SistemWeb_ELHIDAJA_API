package com.hidajasac.hidajasac_apis.presentation.controller.documentoIdentidad.Documento;

import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.DocumentoD.DocumentoIdentidadCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.DocumentoD.DocumentoIdentidadResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.DocumentoD.DocumentoIdentidadUpdateDTO;
import com.hidajasac.hidajasac_apis.service.implementacion.documentoIdentidad.DocumentoImp.DocumentoIdentidadImp;
import com.hidajasac.hidajasac_apis.util.genericResponse.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tipo_documento")
public  class DocumentoIdentidadControler {
    private final DocumentoIdentidadImp service;

    public DocumentoIdentidadControler(DocumentoIdentidadImp service) {
        this.service = service;
    }

    //findAll
    @GetMapping("/get_all")
    public ResponseEntity<ApiResponse<List<DocumentoIdentidadResponseDTO>>> getAll() {
        List<DocumentoIdentidadResponseDTO> list = service.getAll();
        return ResponseEntity.ok(new ApiResponse<>(list));
    }

    //getById
    @GetMapping("/find_by_id/{id}")
    public ResponseEntity<ApiResponse<DocumentoIdentidadResponseDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(service.getById(id)));
    }

    //create
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<DocumentoIdentidadResponseDTO>> create(@Valid @RequestBody DocumentoIdentidadCreateDTO dto) {
        return new ResponseEntity<>(new ApiResponse<>(service.create(dto)), HttpStatus.CREATED);
    }

    //update
    @PatchMapping("/update/{id}")
    public ResponseEntity<ApiResponse<DocumentoIdentidadResponseDTO>> update(@PathVariable Long id, @Valid @RequestBody DocumentoIdentidadUpdateDTO dto) {
        return ResponseEntity.ok(new ApiResponse<>(service.update(id, dto)));
    }

    //deactivate
    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse<DocumentoIdentidadResponseDTO>> deactivate(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(service.deactivate(id)));
    }

    //activate
    @PatchMapping("/activate/{id}")
    public ResponseEntity<ApiResponse<DocumentoIdentidadResponseDTO>> activate(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(service.activate(id)));
    }
}
