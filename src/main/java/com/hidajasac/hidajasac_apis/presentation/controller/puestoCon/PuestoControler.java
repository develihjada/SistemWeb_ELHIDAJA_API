package com.hidajasac.hidajasac_apis.presentation.controller.puestoCon;

import com.hidajasac.hidajasac_apis.presentation.dto.puestoD.PuestoCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.puestoD.PuestoResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.puestoD.PuestoUpdateDTO;
import com.hidajasac.hidajasac_apis.service.implementacion.puestoImp.PuestoImp;
import com.hidajasac.hidajasac_apis.util.genericResponse.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/puesto")
public class PuestoControler {

    private final PuestoImp puestoService;

    public PuestoControler(PuestoImp puestoService) {
        this.puestoService = puestoService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<ApiResponse<List<PuestoResponseDTO>>> getAll() {
        List<PuestoResponseDTO> list = puestoService.getAllS();
        return ResponseEntity.ok(new ApiResponse<>(list));
    }

    @GetMapping("/find_by_id/{id}")
    public ResponseEntity<ApiResponse<PuestoResponseDTO>> getById(@PathVariable Long id) {
        PuestoResponseDTO dto = puestoService.getByIdS(id);
        return ResponseEntity.ok(new ApiResponse<>(dto));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<PuestoResponseDTO>> create(@Valid @RequestBody PuestoCreateDTO dto) {
        PuestoResponseDTO created = puestoService.saveSer(dto);
        return new ResponseEntity<>(new ApiResponse<>(created), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ApiResponse<PuestoResponseDTO>> update(@Valid @RequestBody PuestoUpdateDTO dto, @PathVariable Long id) {
        PuestoResponseDTO updated = puestoService.updateSer(dto, id);
        return ResponseEntity.ok(new ApiResponse<>(updated));
    }

    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse<PuestoResponseDTO>> deactivate(@PathVariable Long id) {
        PuestoResponseDTO updated = puestoService.deactivateSer(id);
        return ResponseEntity.ok(new ApiResponse<>(updated));
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<ApiResponse<PuestoResponseDTO>> activate(@PathVariable Long id) {
        PuestoResponseDTO updated = puestoService.activateSer(id);
        return ResponseEntity.ok(new ApiResponse<>(updated));
    }
}