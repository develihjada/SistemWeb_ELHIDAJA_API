package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.unidadMedidaProducto.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedidaProducto.Response.*;

public interface UnidadMedidaProductoDAO {

    public ResponseProductoUnidadMedidaAllDTO getAllD(RequestProductoUnidadMedidaOptionDTO option);

    public ResponseDetalleProductoUnidadMedidaDTO getByIdD(RequestProductoUnidadMedidaIdDTO id);

    public ResponserProductoUnidadMedidaMensajeDTO desactivateD(RequestProductoUnidadMedidaIdDTO id);

    public ResponserProductoUnidadMedidaMensajeDTO activateD(RequestProductoUnidadMedidaIdDTO id);

    public ResponserProductoUnidadMedidaMensajeDTO insertD(RequestProductoUnidadMedidaInsertDTO objProductoUnidadMedida);

    public ResponserProductoUnidadMedidaMensajeDTO updateD(RequestProductoUnidadMedidaUpdateDTO objProductoUnidadMedida);
}
