package com.escalab.mediappbackend.service.impl;

import com.escalab.mediappbackend.exception.ModeloNotFoundException;
import com.escalab.mediappbackend.model.Paciente;
import com.escalab.mediappbackend.repository.PacienteRepository;
import com.escalab.mediappbackend.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public List<Paciente> listar() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente leerPorId(Integer id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if(paciente.isPresent()){
            return paciente.get();
        }else{
            throw new ModeloNotFoundException("El Paciente no fue encontrado, o su valor es nulo");
        }
    }

    @Override
    public List<Paciente> findAllByName(String nombre) {
        return pacienteRepository.findAllByNombres(nombre);
    }

    @Override
    public Paciente registrar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente modificar(Paciente paciente) {
        Optional<Paciente> findPaciente = pacienteRepository.findById(paciente.getIdPaciente());
        int variable = 0/2;
        if(findPaciente.isPresent()){
            return modificar(paciente);
        }else{
            return new Paciente();
        }
    }

    @Override
    public boolean eliminar(Integer id){
        pacienteRepository.deleteById(id);
        return true;
    }

    @Override
    public Page<Paciente> listarPageable(Pageable pageable) {
        return pacienteRepository.findAll(pageable);
    }

    @Override
    public ResponseEntity<Object> deletePaciente(Integer id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if(paciente.isPresent()){
            pacienteRepository.delete(paciente.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            throw new ModeloNotFoundException("El Paciente no fue encontrado, o su valor es nulo");
        }
    }
}
