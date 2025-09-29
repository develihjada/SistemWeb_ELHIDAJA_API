package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.estante.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.estante.Request.*;

public interface EstanteDAO {
    public ResponseEstanteAllDTO getAllD(RequestEstanteOptionDTO option);

    public ResponseDetalleEstanteDTO getByIdD(RequestEstanteIdDTO id);

    public ResponseEstanteMensajeDTO desactivateD(RequestEstanteIdDTO id);

    public ResponseEstanteMensajeDTO activateD(RequestEstanteIdDTO id);

    public ResponseEstanteMensajeDTO insertD(RequestEstanteInsertDTO objEstante);

    public ResponseEstanteMensajeDTO updateD(RequestEstanteUpdateDTO objEstante);
}
