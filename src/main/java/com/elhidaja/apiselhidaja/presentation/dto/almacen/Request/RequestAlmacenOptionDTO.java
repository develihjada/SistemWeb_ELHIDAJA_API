package com.elhidaja.apiselhidaja.presentation.dto.almacen.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;
import lombok.*;
import jakarta.validation.constraints.NotNull;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAlmacenOptionDTO {
    @NotNull(message = "La opción es obligatoria")
    @ValidOption
    private Long option;
}
