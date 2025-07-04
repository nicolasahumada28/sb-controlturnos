package com.timmynet.controlturnos.sb_controlturnos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.timmynet.controlturnos.sb_controlturnos.model.entity.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long>{
    Optional<Colaborador> findByEmail(String email);

}
