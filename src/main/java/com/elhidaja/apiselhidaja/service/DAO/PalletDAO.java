package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.pallet.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.pallet.Response.*;

public interface PalletDAO {
       ResponsePalletAllDTO getAllD(RequestPalletOptionDTO option);

    ResponseDetallePalletDTO getByIdD(RequestPalletIdDTO id);

    ResponserPalletMensajeDTO desactivateD(RequestPalletIdDTO id);

    ResponserPalletMensajeDTO activateD(RequestPalletIdDTO id);

    ResponserPalletMensajeDTO insertD(RequestPalletInsertDTO objPallet);

    ResponserPalletMensajeDTO updateD(RequestPalletUpdateDTO objPallet);
}
