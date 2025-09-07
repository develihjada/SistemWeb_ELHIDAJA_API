package com.hidajasac.hidajasac_apis.presentation.controller.nivelAcademicoCon;

import com.hidajasac.hidajasac_apis.presentation.dto.nivelAcademicoD.NivelAcademicoCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.nivelAcademicoD.NivelAcademicoResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.nivelAcademicoD.NivelAcademicoUpdateDTO;
import com.hidajasac.hidajasac_apis.service.implementacion.nivelAcademicoImp.NivelAcademicoImp;
import com.hidajasac.hidajasac_apis.util.genericResponse.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NivelAcademicoControler  {
    private final NivelAcademicoImp nivelService;

    public NivelAcademicoControler(NivelAcademicoImp nivelService) {
        this.nivelService = nivelService;
    }

    //findAll
    @GetMapping("/get_all")
    public ResponseEntity<ApiResponse<List<NivelAcademicoResponseDTO>>> getAll() {
        List<NivelAcademicoResponseDTO> niveles = nivelService.getAllS();
        return ResponseEntity.ok(new ApiResponse<>(niveles));
    }

    //findById
    @GetMapping("/find_by_id/{id}")
    public ResponseEntity<ApiResponse<NivelAcademicoResponseDTO>> getById(@PathVariable Long id) {
        NivelAcademicoResponseDTO nivel = nivelService.getByIdS(id);
        return ResponseEntity.ok(new ApiResponse<>(nivel));
    }

    //create
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<NivelAcademicoResponseDTO>> create(@Valid @RequestBody NivelAcademicoCreateDTO dto) {
        NivelAcademicoResponseDTO created = nivelService.saveSer(dto);
        return new ResponseEntity<>(new ApiResponse<>(created), HttpStatus.CREATED);
    }

    //update
    @PatchMapping("/update/{id}")
    public ResponseEntity<ApiResponse<NivelAcademicoResponseDTO>> update(@Valid @RequestBody NivelAcademicoUpdateDTO dto, @PathVariable Long id) {
        NivelAcademicoResponseDTO updated = nivelService.updateSer(dto, id);
        return ResponseEntity.ok(new ApiResponse<>(updated));
    }

    //deactivate
    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse<NivelAcademicoResponseDTO>> deactivate(@PathVariable Long id) {
        NivelAcademicoResponseDTO updated = nivelService.desactivateSer(id);
        return ResponseEntity.ok(new ApiResponse<>(updated));
    }

    //activate
    @PatchMapping("/activate/{id}")
    public ResponseEntity<ApiResponse<NivelAcademicoResponseDTO>> activate(@PathVariable Long id) {
        NivelAcademicoResponseDTO updated = nivelService.activateSer(id);
        return ResponseEntity.ok(new ApiResponse<>(updated));
    }
}
