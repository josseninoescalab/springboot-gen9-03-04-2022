package com.escalab.mediappbackend.repository;

import com.escalab.mediappbackend.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    List<Paciente> findAllByNombres(String nombres);
}
