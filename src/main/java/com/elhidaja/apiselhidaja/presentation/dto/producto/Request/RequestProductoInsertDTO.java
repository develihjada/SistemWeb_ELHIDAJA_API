package com.elhidaja.apiselhidaja.presentation.dto.producto.Request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestProductoInsertDTO {

    @NotBlank(message = "El código del producto no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z0-9\\-\\s]+$", message = "El código del producto solo puede contener letras, números, espacios y guiones")
    private String codigo;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre del producto contener letras y espacios")
    private String nombre;

    private String imagen;

    @NotBlank(message = "El código de barras no puede estar vacío")
    private String codigoBarras;

    @NotBlank(message = "la descripcion  estar vacía")
    private String descripcion;

    @NotNull(message = "La subcategoría es obligatoria")
    @Min(value = 1, message = "ID de subcategoría no válido")
    private Long idSubcategoria;

    // @NotNull(message = "El pallet es obligatorio")
    @Min(value = 1, message = "ID de pallet no válido")
    private Long idPallet;

    @NotNull(message = "El costo es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El costo debe ser mayor que cero")
    private BigDecimal costo;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

}
