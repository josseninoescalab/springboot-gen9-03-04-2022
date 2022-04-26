package com.escalab.mediappbackend.service;

import java.util.List;

import com.escalab.mediappbackend.model.ConsultaExamen;

public interface IConsultaExamenService  {
	
	
	List<ConsultaExamen> listarExamenesPorConsulta(Integer idConsulta);
	
	

}
