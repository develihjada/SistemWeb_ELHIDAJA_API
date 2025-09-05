package com.hidajasac.hidajasac_apis.presentation.controller.enumsCon;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.enums.EstadoCivil;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.enums.Genero;
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
    public List<String> getGeneros() {
        return Arrays.stream(Genero.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/estado-civil")
    public List<String> getEstadoCivil() {
        return Arrays.stream(EstadoCivil.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

}
