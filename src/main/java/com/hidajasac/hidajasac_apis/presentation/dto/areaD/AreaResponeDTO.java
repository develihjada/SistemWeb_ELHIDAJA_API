package com.hidajasac.hidajasac_apis.presentation.dto.areaD;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaResponeDTO {
    private Long id;
    private String nombre;
    private boolean status;
}
