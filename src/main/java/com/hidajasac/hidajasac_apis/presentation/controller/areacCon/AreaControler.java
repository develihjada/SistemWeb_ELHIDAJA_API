package com.hidajasac.hidajasac_apis.presentation.controller.areacCon;

import com.hidajasac.hidajasac_apis.presentation.dto.areaD.AreaCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.areaD.AreaResponeDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.areaD.AreaUpdateDTO;
import com.hidajasac.hidajasac_apis.service.implementacion.areacImp.AreaImp;
import com.hidajasac.hidajasac_apis.util.genericResponse.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/area")
public class AreaControler {

    private final AreaImp areaService;

    public AreaControler(AreaImp areaService) {
        this.areaService = areaService;
    }

    //findAll
    @GetMapping("/get_all")
    public ResponseEntity<ApiResponse<List<AreaResponeDTO>>> getAll() {
        List<AreaResponeDTO> areas = areaService.getAllS();
        return ResponseEntity.ok(new ApiResponse<>(areas));
    }

    //getById
    @GetMapping("/find_by_id/{id}")
    public ResponseEntity<ApiResponse<AreaResponeDTO>> getById(@PathVariable Long id) {
        AreaResponeDTO area = areaService.getByIdS(id);
        return ResponseEntity.ok(new ApiResponse<>(area));
    }

    //create
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<AreaResponeDTO>> create(@Valid @RequestBody AreaCreateDTO areaCreateDTO) {
        AreaResponeDTO created = areaService.saveSer(areaCreateDTO);
        return new ResponseEntity<>(new ApiResponse<>(created), HttpStatus.CREATED);
    }

    //update
    @PatchMapping("/update/{id}")
    public ResponseEntity<ApiResponse<AreaResponeDTO>> update(@Valid @RequestBody AreaUpdateDTO areaUpdateDTO, @PathVariable Long id) {
        AreaResponeDTO updated = areaService.updateSer(areaUpdateDTO, id);
        return ResponseEntity.ok(new ApiResponse<>(updated));
    }

    // delete
    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse<AreaResponeDTO>> desactivate(@PathVariable Long id) {
        AreaResponeDTO updated =  areaService.desactivateSer(id);
        return ResponseEntity.ok(new ApiResponse<>(updated));
    }

    // delete
    @PatchMapping("/activate/{id}")
    public ResponseEntity<ApiResponse<AreaResponeDTO>> activate(@PathVariable Long id) {
        AreaResponeDTO updated= areaService.activateSer(id);
        return ResponseEntity.ok(new ApiResponse<>(updated));
    }
}
