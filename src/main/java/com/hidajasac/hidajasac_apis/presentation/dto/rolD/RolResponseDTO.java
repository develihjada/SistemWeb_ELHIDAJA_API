package com.hidajasac.hidajasac_apis.presentation.dto.rolD;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolResponseDTO {

    private  Long id;

    private String tipoRol;

    private boolean status;
}
