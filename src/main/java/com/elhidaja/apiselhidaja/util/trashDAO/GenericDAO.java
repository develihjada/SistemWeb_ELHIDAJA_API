package com.elhidaja.apiselhidaja.util.trashDAO;

import java.util.List;
import java.util.Optional;

public interface GenericDAO <T,K>{

    Optional<K> insert(T object) ;

    Optional<K> update(T object) ;

    List<K> getAll(T object) ;

    Optional<K> deactivate(T object) ;

    Optional<K> activate(T object) ;

    Optional<K> getById(T object) ;
    
}
