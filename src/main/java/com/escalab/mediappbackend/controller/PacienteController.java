package com.escalab.mediappbackend.controller;

import com.escalab.mediappbackend.model.Paciente;
import com.escalab.mediappbackend.service.PacienteService;
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
@RequestMapping(value = "/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public List<Paciente> findAll(){
        return pacienteService.findAll();
    }

    @GetMapping("/{id}")
    public Paciente findById(@PathVariable("id") Integer id){
        return pacienteService.findById(id);
    }

    @GetMapping("/nombre/{nombre}")
    public List<Paciente> findAllByName(@PathVariable("nombre") String nombre){
        return pacienteService.findAllByName(nombre);
    }

    @PostMapping
    public Paciente save(@RequestBody Paciente paciente){
        return pacienteService.save(paciente);
    }

    @PutMapping("/{id}")
    public Paciente update(@RequestBody Paciente paciente, @PathVariable("id") Integer id){
        return pacienteService.update(paciente, id);
    }
}
