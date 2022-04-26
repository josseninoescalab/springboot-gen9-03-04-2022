package com.escalab.mediappbackend.controller;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.escalab.mediappbackend.model.ConsultaExamen;
import com.escalab.mediappbackend.service.IConsultaExamenService;

@RestController
@RequestMapping("/consultaexamenes")
public class ConsultaExamenController {

	@Autowired
	private IConsultaExamenService service;

	@GetMapping(value = "/{idConsulta}")
	public ResponseEntity<List<ConsultaExamen>> listar(@PathVariable("idConsulta") Integer idconsulta) {
		List<ConsultaExamen> consultasexamen = new ArrayList<>();
		consultasexamen = service.listarExamenesPorConsulta(idconsulta);
		return new ResponseEntity<List<ConsultaExamen>>(consultasexamen, HttpStatus.OK);
	}
}
