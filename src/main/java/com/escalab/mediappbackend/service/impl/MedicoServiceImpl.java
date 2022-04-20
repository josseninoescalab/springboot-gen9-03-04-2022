package com.escalab.mediappbackend.service.impl;

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
    public List<Medico> findAll() {
        return medicoRepository.findAll();
    }

    @Override
    public Medico findById(Integer id) {
        Optional<Medico> medico = medicoRepository.findById(id);
        return medico.isPresent() ? medico.get() : new Medico();
    }

    @Override
    public Medico save(Medico medico) {
        return medicoRepository.save(medico);
    }

    @Override
    public Medico update(Medico medico, Integer id) {
        return save(medico);
    }

    @Override
    public boolean deleteById(Integer id) {
        medicoRepository.deleteById(id);
        return true;
    }
}
