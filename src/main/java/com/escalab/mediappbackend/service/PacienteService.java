package com.escalab.mediappbackend.service;

import com.escalab.mediappbackend.model.Paciente;

import java.util.List;

public interface PacienteService extends ICRUD<Paciente>{

    List<Paciente> findAllByName(String nombre);
}
