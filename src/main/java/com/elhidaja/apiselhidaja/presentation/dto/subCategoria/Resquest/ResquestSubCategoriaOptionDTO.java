package com.elhidaja.apiselhidaja.presentation.dto.subCategoria.Resquest;
import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResquestSubCategoriaOptionDTO {

    @NotNull(message = "La opcion es obligatoria")
    @ValidOption
    private Long option;
}
