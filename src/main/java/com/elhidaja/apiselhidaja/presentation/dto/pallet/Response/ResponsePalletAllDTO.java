package com.elhidaja.apiselhidaja.presentation.dto.pallet.Response;

import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "pallets", "exito", "mensaje",  "codigo" })
public class ResponsePalletAllDTO  extends GlobalResponse{
        List<ResponsePalletDTO> pallets;
}
