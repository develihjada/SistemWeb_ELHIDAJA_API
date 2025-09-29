package com.elhidaja.apiselhidaja.util.genericresponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ApiResponse<T> {
    private T data;
    private boolean exito;
    private String msge;

}
