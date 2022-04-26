package com.escalab.mediappbackend.service.impl;

import com.escalab.mediappbackend.exception.ModeloNotFoundException;
import com.escalab.mediappbackend.model.Medico;
import com.escalab.mediappbackend.repository.MedicoRepository;
import com.escalab.mediappbackend.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public List<Medico> listar() {
        return medicoRepository.findAll();
    }

    @Override
    public Medico leerPorId(Integer id) {
        Optional<Medico> medico = medicoRepository.findById(id);
        if(medico.isPresent()){
            return medico.get();
        }else{
            throw new ModeloNotFoundException("Error al encontrar al Medico");
        }
    }

    @Override
    public Medico registrar(Medico medico) {
        return medicoRepository.save(medico);
    }

    @Override
    public Medico modificar(Medico medico) {
        return registrar(medico);
    }

    @Override
    public boolean eliminar(Integer id) {
        medicoRepository.deleteById(id);
        return true;
    }
}
