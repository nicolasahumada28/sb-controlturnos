package com.timmynet.controlturnos.sb_controlturnos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.timmynet.controlturnos.sb_controlturnos.model.entity.Colaborador;

public interface ColaboradorService {
    public Page<Colaborador> listarColaboradores(Pageable pageable);
    public List<Colaborador> listarColaboradoresSinPag();
    public Optional<Colaborador> colaboradorPorId(Long id);
    public Colaborador guardarColaborador(Colaborador colaborador);
    public void eliminarColaborador(Long id);

    //Servicios adicionales
    public Optional<Colaborador> buscarPorEmail(String email);
    public Long count();
}
