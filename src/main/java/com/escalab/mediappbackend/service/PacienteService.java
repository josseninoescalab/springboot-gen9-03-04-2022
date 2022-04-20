package com.escalab.mediappbackend.service;

import com.escalab.mediappbackend.model.Paciente;

import java.util.List;

public interface PacienteService {
    List<Paciente> findAll();

    Paciente save(Paciente paciente);

    List<Paciente> findAllByName(String nombre);

    Paciente update(Paciente paciente, Integer id) throws Exception;

    Paciente findById(Integer id);
}
