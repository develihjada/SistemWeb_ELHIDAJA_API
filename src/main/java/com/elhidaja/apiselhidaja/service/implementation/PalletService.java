package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.persistence.repository.PalletRepository;
import com.elhidaja.apiselhidaja.presentation.dto.pallet.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.pallet.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.PalletDAO;

@Service
@Validated
public class PalletService {
    
    private final PalletDAO palletRepo;

    public PalletService(PalletRepository palletRepo) {
        this.palletRepo = palletRepo;
    }

    @Transactional
    public ResponserPalletMensajeDTO insertSer(RequestPalletInsertDTO objPallet) {
        return palletRepo.insertD(objPallet);
    }

    @Transactional
    public ResponserPalletMensajeDTO updateSer(RequestPalletUpdateDTO objPallet) {
        return palletRepo.updateD(objPallet);
    }

    @Transactional
    public ResponsePalletAllDTO getAllSer(RequestPalletOptionDTO option) {
        return palletRepo.getAllD(option);
    }

    @Transactional
    public ResponseDetallePalletDTO getByIdSer(RequestPalletIdDTO id) {
        return palletRepo.getByIdD(id);
    }

    @Transactional
    public ResponserPalletMensajeDTO activateSer(RequestPalletIdDTO id) {
        return palletRepo.activateD(id);
    }

    @Transactional
    public ResponserPalletMensajeDTO desactivateSer(RequestPalletIdDTO id) {
        return palletRepo.desactivateD(id);
    }
}
