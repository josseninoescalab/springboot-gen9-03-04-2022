package com.escalab.mediappbackend.service;

import com.escalab.mediappbackend.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PacienteService extends ICRUD<Paciente>{

    List<Paciente> findAllByName(String nombre);

    Page<Paciente> listarPageable(Pageable pageable);

    ResponseEntity<Object> deletePaciente(Integer id);
}
