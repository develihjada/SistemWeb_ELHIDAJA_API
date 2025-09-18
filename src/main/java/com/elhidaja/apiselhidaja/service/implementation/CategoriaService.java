package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.persistence.repository.CategoriaRepository;
import com.elhidaja.apiselhidaja.presentation.dto.categoria.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.categoria.Resquest.*;
import com.elhidaja.apiselhidaja.service.implementation.CategoriaService;

@Service
@Validated
public class CategoriaService {

    private final CategoriaRepository catRepo;

    public CategoriaService(CategoriaRepository catRepo) {
        this.catRepo = catRepo;
    }

    @Transactional
    public ResponserCategoriaMensajeDTO insertSer(RequestCategoriaInsertDTO objCategoria) {
        return catRepo.insertD(objCategoria);
    }

    @Transactional
    public ResponserCategoriaMensajeDTO updateSer(RequestCategoriaUpdateDTO objCategoria) {
        return catRepo.updateD(objCategoria);
    }

    @Transactional
    public ResponseCategoriAllDTO getAllSer(ResquestCategoriaOptionDTO option) {
        return catRepo.getAllD(option);
    }

    @Transactional
    public ResponseDetalleCategoriaDTO getByIdSer(RequesteCategoriaIdDTO id) {
       
        return catRepo.getByIdD(id);
    }

    @Transactional
    public ResponserCategoriaMensajeDTO activateSer(RequesteCategoriaIdDTO id) {
        return catRepo.activateD(id);
    }

    @Transactional
    public ResponserCategoriaMensajeDTO desactivateSer(RequesteCategoriaIdDTO id) {
        return catRepo.desactivateD(id);
    }
}
