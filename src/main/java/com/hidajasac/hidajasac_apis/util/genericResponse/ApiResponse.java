package com.hidajasac.hidajasac_apis.util.genericResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private T data;
    private boolean exito;
    private String msge;

    // solo objeto
    public ApiResponse(T data) {
        this.data = data;
        this.exito = true;
        this.msge = "--ok--";
    }
    //error
    public ApiResponse(String errorMessage, boolean exito) {
        this.data = null;
        this.exito = exito;
        this.msge = errorMessage;
    }

}
