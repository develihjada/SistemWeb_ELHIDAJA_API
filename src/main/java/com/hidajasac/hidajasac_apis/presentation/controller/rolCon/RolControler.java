package com.hidajasac.hidajasac_apis.presentation.controller.rolCon;
import com.hidajasac.hidajasac_apis.presentation.dto.rolD.RolCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.rolD.RolResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.rolD.RolUpdateDTO;
import com.hidajasac.hidajasac_apis.service.implementacion.rolImp.RolImp;
import com.hidajasac.hidajasac_apis.util.genericResponse.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rol")
public class RolControler {

    private final RolImp rolService;

    public RolControler(RolImp rolService) {
        this.rolService = rolService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<ApiResponse<List<RolResponseDTO>>> getAll() {
        List<RolResponseDTO> roles = rolService.getAllS();
        return ResponseEntity.ok(new ApiResponse<>(roles));
    }

    @GetMapping("/find_by_id/{id}")
    public ResponseEntity<ApiResponse<RolResponseDTO>> getById(@PathVariable Long id) {
        RolResponseDTO rol = rolService.getByIdS(id);
        return ResponseEntity.ok(new ApiResponse<>(rol));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<RolResponseDTO>> create(@Valid @RequestBody RolCreateDTO rolCreateDTO) {
        RolResponseDTO created = rolService.saveSer(rolCreateDTO);
        return new ResponseEntity<>(new ApiResponse<>(created), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ApiResponse<RolResponseDTO>> update(@Valid @RequestBody RolUpdateDTO rolUpdateDTO, @PathVariable Long id) {
        RolResponseDTO updated = rolService.updateSer(rolUpdateDTO, id);
        return ResponseEntity.ok(new ApiResponse<>(updated));
    }

    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse<RolResponseDTO>> deactivate(@PathVariable Long id) {
        RolResponseDTO updated = rolService.desactivateSer(id);
        return ResponseEntity.ok(new ApiResponse<>(updated));
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<ApiResponse<RolResponseDTO>> activate(@PathVariable Long id) {
        RolResponseDTO updated = rolService.activateSer(id);
        return ResponseEntity.ok(new ApiResponse<>(updated));
    }
}