package com.elhidaja.apiselhidaja.presentation.dto.estante.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;
import lombok.*;
import jakarta.validation.constraints.NotNull;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestEstanteOptionDTO {
    @NotNull(message = "La opción es obligatoria")
    @ValidOption
    private Long option;
}
