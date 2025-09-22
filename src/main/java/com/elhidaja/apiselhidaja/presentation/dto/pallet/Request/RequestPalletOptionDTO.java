package com.elhidaja.apiselhidaja.presentation.dto.pallet.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class RequestPalletOptionDTO {
    @NotNull(message = "La opción es obligatoria")
    @ValidOption 
    private Long option;
}
