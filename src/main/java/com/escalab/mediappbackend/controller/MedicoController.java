package com.escalab.mediappbackend.controller;

import com.escalab.mediappbackend.model.Medico;
import com.escalab.mediappbackend.model.Paciente;
import com.escalab.mediappbackend.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public List<Medico> findAll(){
        return medicoService.listar();
    }

    @GetMapping("/{id}")
    public Medico findById(@PathVariable("id") Integer id){
        return medicoService.leerPorId(id);
    }

    @PostMapping
    public Medico save(@RequestBody Medico medico){
        return medicoService.registrar(medico);
    }

    @PutMapping("/{id}")
    public Medico update(@RequestBody Medico medico, @PathVariable("id") Integer id){
        return medicoService.modificar(medico);
    }
}
