package com.escalab.mediappbackend.controller;

import com.escalab.mediappbackend.model.Paciente;
import com.escalab.mediappbackend.service.PacienteService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Obtener todos los pacientes",
            notes = "No necesita parametros de entrada",
            responseContainer = "Pacientes")
    @ApiResponse(code = 200, message = "ApiResponseMessages.ITEM_FETCHED",
            response = Paciente.class, responseContainer = "List"
    )
    @ApiImplicitParams({ @ApiImplicitParam(name = "foo", value = "List of strings", paramType = "body", dataType =
            "Foo") })
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
            @ApiResponse(code = 404, message = "Not found, no encontrado"),
            @ApiResponse(code = 405, message = "No se encontraron pacientes en la BD"),
            @ApiResponse(code = 200, message = "Peticón OK")})
    @GetMapping
    public List<Paciente> findAll(){
        return pacienteService.listar();
    }

    @ApiOperation(value = "Obtener todos el pacientes por su identificador",
            notes = "No necesita parametros de entrada",
            response = Paciente.class,
            responseContainer = "Paciente")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
            @ApiResponse(code = 404, message = "Not found, no encontrado"),
            @ApiResponse(code = 405, message = "No se encontraron pacientes en la BD"),
            @ApiResponse(code = 200, message = "Peticón OK")})
    @GetMapping("/{id}")
    public Paciente findById(@PathVariable("id") Integer id){
        return pacienteService.leerPorId(id);
    }

    @GetMapping("/nombre/{nombre}")
    public List<Paciente> findAllByName(@PathVariable("nombre") String nombre){
        return pacienteService.findAllByName(nombre);
    }

    @PostMapping
    public Paciente save(@RequestBody Paciente paciente){
        return pacienteService.registrar(paciente);
    }

    @PutMapping("/{id}")
    public Paciente update(@RequestBody Paciente paciente, @PathVariable("id") Integer id){
        return pacienteService.modificar(paciente);
    }
}
