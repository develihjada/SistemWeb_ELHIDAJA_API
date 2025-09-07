package com.hidajasac.hidajasac_apis.presentation.controller.documentoIdentidad.Nacionalidad;

import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD.NacionalidadCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD.NacionalidadResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.documentoIdentidad.NacionalidadD.NacionalidadUpdateDTO;
import com.hidajasac.hidajasac_apis.service.implementacion.documentoIdentidad.NacionalidadImp.NacionalidadImp;
import com.hidajasac.hidajasac_apis.util.genericResponse.ApiResponse;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nacionalidad")
public class NacionalidadControler {
    private final NacionalidadImp service;

    public NacionalidadControler(NacionalidadImp service) {
        this.service = service;
    }

    //findAll
    @GetMapping("/get_all")
    public ResponseEntity<ApiResponse<List<NacionalidadResponseDTO>>> getAll() {
        List<NacionalidadResponseDTO> list = service.getAll();
        return ResponseEntity.ok(new ApiResponse<>(list));
    }

    //getById
    @GetMapping("/find_by_id/{id}")
    public ResponseEntity<ApiResponse<NacionalidadResponseDTO>> getById(@PathVariable Long id) {
        NacionalidadResponseDTO dto = service.getById(id);
        return ResponseEntity.ok(new ApiResponse<>(dto));
    }

    //create
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<NacionalidadResponseDTO>> create(@Valid @RequestBody NacionalidadCreateDTO dto) {
        NacionalidadResponseDTO created = service.save(dto);
        return new ResponseEntity<>(new ApiResponse<>(created), HttpStatus.CREATED);
    }

    //update
    @PatchMapping("/update/{id}")
    public ResponseEntity<ApiResponse<NacionalidadResponseDTO>> update(@Valid @RequestBody NacionalidadUpdateDTO dto, @PathVariable Long id) {
        NacionalidadResponseDTO updated = service.update(id, dto);
        return ResponseEntity.ok(new ApiResponse<>(updated));
    }

    //deactivate
    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse<NacionalidadResponseDTO>> deactivate(@PathVariable Long id) {
        NacionalidadResponseDTO updated = service.deactivate(id);
        return ResponseEntity.ok(new ApiResponse<>(updated));
    }

    //activate
    @PatchMapping("/activate/{id}")
    public ResponseEntity<ApiResponse<NacionalidadResponseDTO>> activate(@PathVariable Long id) {
        NacionalidadResponseDTO updated = service.activate(id);
        return ResponseEntity.ok(new ApiResponse<>(updated));
    }
}
