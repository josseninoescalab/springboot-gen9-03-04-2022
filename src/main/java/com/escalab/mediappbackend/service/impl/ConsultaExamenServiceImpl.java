package com.escalab.mediappbackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.mediappbackend.model.ConsultaExamen;
import com.escalab.mediappbackend.repo.IConsultaExamenRepo;
import com.escalab.mediappbackend.service.IConsultaExamenService;

@Service
public class ConsultaExamenServiceImpl implements IConsultaExamenService {
	
	@Autowired
	private IConsultaExamenRepo repo;
	
	@Override
	public List<ConsultaExamen> listarExamenesPorConsulta(Integer idconsulta) {
		return repo.listarExamenesPorConsulta(idconsulta);
	}
}
