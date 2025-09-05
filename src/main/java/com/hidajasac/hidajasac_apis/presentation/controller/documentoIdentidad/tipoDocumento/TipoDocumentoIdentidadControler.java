package com.hidajasac.hidajasac_apis.presentation.controller.documentoIdentidad.tipoDocumento;

import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.tipoDocumentoD.TipoDocumentoIdentidadCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.tipoDocumentoD.TipoDocumentoIdentidadResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.tipoDocumentoD.TipoDocumentoIdentidadUpdateDTO;
import com.hidajasac.hidajasac_apis.service.implementacion.documentoIdentidad.tipoDocumento.TipoDocumentoIdentidadImp;
import com.hidajasac.hidajasac_apis.util.genericResponse.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tipo_documento")
public  class TipoDocumentoIdentidadControler {
    private final TipoDocumentoIdentidadImp service;

    public TipoDocumentoIdentidadControler(TipoDocumentoIdentidadImp service) {
        this.service = service;
    }

    //findAll
    @GetMapping("/get_all")
    public ResponseEntity<ApiResponse<TipoDocumentoIdentidadResponseDTO>> getAll() {
        List<TipoDocumentoIdentidadResponseDTO> list = service.getAll();
        return ResponseEntity.ok(new ApiResponse<>(list));
    }

    //getById
    @GetMapping("/find_by_id/{id}")
    public ResponseEntity<ApiResponse<TipoDocumentoIdentidadResponseDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(service.getById(id)));
    }

    //create
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<TipoDocumentoIdentidadResponseDTO>> create(@Valid @RequestBody TipoDocumentoIdentidadCreateDTO dto) {
        return new ResponseEntity<>(new ApiResponse<>(service.create(dto)), HttpStatus.CREATED);
    }

    //update
    @PatchMapping("/update/{id}")
    public ResponseEntity<ApiResponse<TipoDocumentoIdentidadResponseDTO>> update(@PathVariable Long id, @Valid @RequestBody TipoDocumentoIdentidadUpdateDTO dto) {
        return ResponseEntity.ok(new ApiResponse<>(service.update(id, dto)));
    }

    //deactivate
    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse<TipoDocumentoIdentidadResponseDTO>> deactivate(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(service.deactivate(id)));
    }

    //activate
    @PatchMapping("/activate/{id}")
    public ResponseEntity<ApiResponse<TipoDocumentoIdentidadResponseDTO>> activate(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(service.activate(id)));
    }
}
