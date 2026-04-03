package com.timmynet.controlturnos.sb_controlturnos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.timmynet.controlturnos.sb_controlturnos.model.entity.Colaborador;
import com.timmynet.controlturnos.sb_controlturnos.repository.ColaboradorRepository;

@Service
public class ColaboradorServiceImpl implements ColaboradorService{

    @Autowired
    ColaboradorRepository repository;

    @Override
    public Page<Colaborador> listarColaboradores(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Colaborador> colaboradorPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Colaborador guardarColaborador(Colaborador colaborador) {
        return repository.save(colaborador);
    }

    @Override
    public void eliminarColaborador(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Colaborador> buscarPorEmail(String email) {
        return repository.buscarPorEmail(email);
    }

    @Override
    public Long count() {
        return repository.count();
    }

    @Override
    public List<Colaborador> listarColaboradoresSinPag() {
        return repository.findAll();
    }

}
