package com.timmynet.controlturnos.sb_controlturnos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.timmynet.controlturnos.sb_controlturnos.model.entity.Gerencia;

public interface GerenciaRepository extends JpaRepository<Gerencia, Long>{
    
}
