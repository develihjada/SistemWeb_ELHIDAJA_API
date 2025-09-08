package com.hidajasac.hidajasac_apis.presentation.controller.enumsCon;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.enums.EstadoCivil;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.enums.Genero;
import com.hidajasac.hidajasac_apis.util.genericResponse.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/enums")
public class EnumControler {

    @GetMapping("/generos")
    public ResponseEntity<ApiResponse<List<String>>> getGeneros() {
        List<String> generos = Arrays.stream(Genero.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>(generos));
    }

    @GetMapping("/estado-civil")
    public  ResponseEntity<ApiResponse<List<String>>> getEstadoCivil() {
        List<String> estados= Arrays.stream(EstadoCivil.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>(estados));
    }

}
