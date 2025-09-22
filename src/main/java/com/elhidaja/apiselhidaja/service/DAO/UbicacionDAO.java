package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.ubicacion.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.ubicacion.Response.*;

public interface UbicacionDAO {
     public ResponseUbicacionAllDTO getAllD(RequestUbicacionOptionDTO option);

    public ResponseDetalleUbicacionDTO getByIdD(RequestUbicacionIdDTO id);

    public ResponserUbicacionMensajeDTO desactivateD(RequestUbicacionIdDTO id);

    public ResponserUbicacionMensajeDTO activateD(RequestUbicacionIdDTO id);

    public ResponserUbicacionMensajeDTO insertD(RequestUbicacionInsertDTO objUbicacion);

    public ResponserUbicacionMensajeDTO updateD(RequestUbicacionUpdateDTO objUbicacion);
}
