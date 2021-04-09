package com.pedro.catalogo.respository;

import com.pedro.catalogo.model.Musica;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogoRepository extends JpaRepository<Musica, Long> {
    
}