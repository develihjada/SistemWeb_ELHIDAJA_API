package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Resquest.*;

public interface UnidadMedidaDAO {
     public ResponseUnidadMedidaAllDTO getAllD(RequestUnidadMedidaOptionDTO option);

    public ResponseDetalleUnidadMedidaDTO getByIdD(RequestUnidadMedidaIdDTO id);

    public ResponseUnidadMedidaMensajeDTO desactivateD(RequestUnidadMedidaIdDTO id);

    public ResponseUnidadMedidaMensajeDTO activateD(RequestUnidadMedidaIdDTO id);

    public ResponseUnidadMedidaMensajeDTO insertD(RequestUnidadMedidaInsertDTO objUnidad);

    public ResponseUnidadMedidaMensajeDTO updateD(RequestUnidadMedidaUpdateDTO objUnidad);
}
