package com.hidajasac.hidajasac_apis.presentation.controller.userCon;

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
@RequestMapping("/user")
public  class UserControler {

    private final UserImp userService;

    public UserControler(UserImp userService) {
        this.userService = userService;
    }

    // findAll
    @GetMapping("/get_all")
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getAll() {
        List<UserResponseDTO> users = userService.getAllS();
        return ResponseEntity.ok(new ApiResponse<>(users));
    }

    // getById
    @GetMapping("/find_by_id/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getById(@PathVariable Long id) {
        UserResponseDTO user = userService.getByIdS(id);
        return ResponseEntity.ok(new ApiResponse<>(user));
    }

    // create
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<UserResponseDTO>> create(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        UserResponseDTO createdUser = userService.saveSer(userCreateDTO);
        return new ResponseEntity<>(new ApiResponse<>(createdUser), HttpStatus.CREATED);
    }

    // update
    @PatchMapping("/update/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> update(@Valid @RequestBody UserUpdateDTO userUpdateDTO, @PathVariable Long id) {
        UserResponseDTO updatedUser = userService.updateSer(userUpdateDTO, id);
        return ResponseEntity.ok(new ApiResponse<>(updatedUser));
    }

    // deactivate
    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> deactivate(@PathVariable Long id) {
        UserResponseDTO deactivatedUser = userService.desactivateSer(id);
        return ResponseEntity.ok(new ApiResponse<>(deactivatedUser));
    }

    // activate
    @PatchMapping("/activate/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> activate(@PathVariable Long id) {
        UserResponseDTO activatedUser = userService.activateSer(id);
        return ResponseEntity.ok(new ApiResponse<>(activatedUser));
    }
}

