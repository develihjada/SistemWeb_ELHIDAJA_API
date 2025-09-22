package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.producto.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.producto.Response.*;

public interface ProductoDAO {
     ResponseProductoAllDTO getAllD(RequestProductoOptionDTO option);

    ResponseDetalleProductoDTO getByIdD(RequestProductoIdDTO id);

    ResponseProductoMensajeDTO insertD(RequestProductoInsertDTO objProducto);

    ResponseProductoMensajeDTO updateD(RequestProductoUpdateDTO objProducto);

    ResponseProductoMensajeDTO desactivateD(RequestProductoIdDTO id);

    ResponseProductoMensajeDTO activateD(RequestProductoIdDTO id);
}
