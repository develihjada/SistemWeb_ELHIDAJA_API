package com.hidajasac.hidajasac_apis.presentation.controller.oficioCon;


import com.hidajasac.hidajasac_apis.presentation.dto.oficioD.OficioCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.oficioD.OficioResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.oficioD.OficioUpdateDTO;
import com.hidajasac.hidajasac_apis.service.implementacion.oficioImp.OficioImp;
import com.hidajasac.hidajasac_apis.util.genericResponse.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oficio")
public class OficioControler {
    private final OficioImp oficioService;

    public OficioControler(OficioImp oficioService) {
        this.oficioService = oficioService;
    }

    //findAll
    @GetMapping("/get_all")
    public ResponseEntity<ApiResponse<OficioResponseDTO>> getAll() {
        List<OficioResponseDTO> data = oficioService.getAll();
        return ResponseEntity.ok(new ApiResponse<>(data));
    }

    //getById
    @GetMapping("/find_by_id/{id}")
    public ResponseEntity<ApiResponse<OficioResponseDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(oficioService.getById(id)));
    }

    //create
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<OficioResponseDTO>> create(@Valid @RequestBody OficioCreateDTO dto) {
        OficioResponseDTO created = oficioService.create(dto);
        return new ResponseEntity<>(new ApiResponse<>(created), HttpStatus.CREATED);
    }

    //update
    @PatchMapping("/update/{id}")
    public ResponseEntity<ApiResponse<OficioResponseDTO>> update(@Valid @RequestBody OficioUpdateDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(oficioService.update(id, dto)));
    }

    //deactivate
    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse<OficioResponseDTO>> deactivate(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(oficioService.deactivate(id)));
    }

    //activate
    @PatchMapping("/activate/{id}")
    public ResponseEntity<ApiResponse<OficioResponseDTO>> activate(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(oficioService.activate(id)));
    }
}
