package com.elhidaja.apiselhidaja.presentation.dto.pallet.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class RequestPalletIdDTO {
    
    @NotNull(message = "El id del pallet es obligatorio")
    @Min(value = 1, message = "El id del pallet debe ser mayor o igual a 1")
    private Long id;
}
