package com.elhidaja.apiselhidaja.presentation.dto.pallet.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "pallet", "exito", "mensaje", "codigo" })
public class ResponseDetallePalletDTO extends GlobalResponse {
    private ResponsePalletDTO pallet;
}
