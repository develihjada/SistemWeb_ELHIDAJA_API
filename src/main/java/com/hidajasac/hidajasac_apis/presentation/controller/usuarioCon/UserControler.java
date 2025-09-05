package com.hidajasac.hidajasac_apis.presentation.controller.usuarioCon;

import com.hidajasac.hidajasac_apis.presentation.dto.usuarioD.UserResponseDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.usuarioD.UserCreateDTO;
import com.hidajasac.hidajasac_apis.presentation.dto.usuarioD.UserUpdateDTO;
import com.hidajasac.hidajasac_apis.service.implementacion.userImp.UserImp;
import com.hidajasac.hidajasac_apis.util.genericResponse.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleado")
public  class UserControler {

    private final UserImp empleadoService;

    public UserControler(UserImp empleadoService) {
        this.empleadoService = empleadoService;
    }

    // findAll
    @GetMapping("/get_all")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getAll() {
        List<UserResponseDTO> empleados = empleadoService.getAllS();
        return ResponseEntity.ok(new ApiResponse<>(empleados));
    }

    // getById
    @GetMapping("/find_by_id/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getById(@PathVariable Long id) {
        UserResponseDTO empleado = empleadoService.getByIdS(id);
        return ResponseEntity.ok(new ApiResponse<>(empleado));
    }

    // create
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<UserResponseDTO>> create(@Valid @RequestBody UserCreateDTO empleadoCreateDTO) {
        UserResponseDTO createdEmpleado = empleadoService.saveSer(empleadoCreateDTO);
        return new ResponseEntity<>(new ApiResponse<>(createdEmpleado), HttpStatus.CREATED);
    }

    // update
    @PatchMapping("/update/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> update(@Valid @RequestBody UserUpdateDTO userUpdateDTO, @PathVariable Long id) {
        UserResponseDTO updatedEmpleado = empleadoService.updateSer(userUpdateDTO, id);
        return ResponseEntity.ok(new ApiResponse<>(updatedEmpleado));
    }

    // deactivate
    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> deactivate(@PathVariable Long id) {
        UserResponseDTO deactivatedEmpleado = empleadoService.desactivateSer(id);
        return ResponseEntity.ok(new ApiResponse<>(deactivatedEmpleado));
    }

    // activate
    @PatchMapping("/activate/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> activate(@PathVariable Long id) {
        UserResponseDTO activatedEmpleado = empleadoService.activateSer(id);
        return ResponseEntity.ok(new ApiResponse<>(activatedEmpleado));
    }
}

