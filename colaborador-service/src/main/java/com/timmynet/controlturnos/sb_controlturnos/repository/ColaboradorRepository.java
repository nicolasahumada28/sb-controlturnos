package com.timmynet.controlturnos.sb_controlturnos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.timmynet.controlturnos.sb_controlturnos.model.entity.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long>{

    @Query("SELECT c FROM Colaborador c WHERE c.email =:email")
    Optional<Colaborador> buscarPorEmail(String email);

    @Query("SELECT c FROM Colaborador c WHERE c.rut LIKE %:rut%")
    Optional<Colaborador> buscarPorRutParcial(String rut);
}
