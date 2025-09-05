package com.hidajasac.hidajasac_apis.presentation.controller.documentoIdentidad.Nacionalidad;

import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD.TipoNacionalidadCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD.TipoNacionalidadResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD.TipoNacionalidadUpdateDTO;
import com.hidajasac.hidajasac_apis.service.implementacion.documentoIdentidad.Nacionalidad.TipoNacionalidadImp;
import com.hidajasac.hidajasac_apis.util.genericResponse.ApiResponse;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nacionalidad")
public class TipoNacionalidadControler {
    private final TipoNacionalidadImp service;

    public TipoNacionalidadControler(TipoNacionalidadImp service) {
        this.service = service;
    }

    //findAll
    @GetMapping("/get_all")
    public ResponseEntity<ApiResponse<TipoNacionalidadResponseDTO>> getAll() {
        List<TipoNacionalidadResponseDTO> list = service.getAll();
        return ResponseEntity.ok(new ApiResponse<>(list));
    }

    //getById
    @GetMapping("/find_by_id/{id}")
    public ResponseEntity<ApiResponse<TipoNacionalidadResponseDTO>> getById(@PathVariable Long id) {
        TipoNacionalidadResponseDTO dto = service.getById(id);
        return ResponseEntity.ok(new ApiResponse<>(dto));
    }

    //create
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<TipoNacionalidadResponseDTO>> create(@Valid @RequestBody TipoNacionalidadCreateDTO dto) {
        TipoNacionalidadResponseDTO created = service.save(dto);
        return new ResponseEntity<>(new ApiResponse<>(created), HttpStatus.CREATED);
    }

    //update
    @PatchMapping("/update/{id}")
    public ResponseEntity<ApiResponse<TipoNacionalidadResponseDTO>> update(@Valid @RequestBody TipoNacionalidadUpdateDTO dto, @PathVariable Long id) {
        TipoNacionalidadResponseDTO updated = service.update(id, dto);
        return ResponseEntity.ok(new ApiResponse<>(updated));
    }

    //deactivate
    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse<TipoNacionalidadResponseDTO>> deactivate(@PathVariable Long id) {
        TipoNacionalidadResponseDTO updated = service.deactivate(id);
        return ResponseEntity.ok(new ApiResponse<>(updated));
    }

    //activate
    @PatchMapping("/activate/{id}")
    public ResponseEntity<ApiResponse<TipoNacionalidadResponseDTO>> activate(@PathVariable Long id) {
        TipoNacionalidadResponseDTO updated = service.activate(id);
        return ResponseEntity.ok(new ApiResponse<>(updated));
    }
}
