package com.hidajasac.hidajasac_apis.util.genericResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private List<T> data;
    private boolean exito;
    private String msge;

    //lista de datos
    public ApiResponse(List<T> data) {
        this.data = data;
        this.exito = true;
        this.msge = "--ok--";
    }

    // solo objeto
    public ApiResponse(T data) {
        this.data = List.of(data);
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
