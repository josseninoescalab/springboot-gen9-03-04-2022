package com.escalab.mediappbackend.service;

import com.escalab.mediappbackend.dto.ConsultaListaExamenDTO;
import com.escalab.mediappbackend.dto.ConsultaResumenDTO;
import com.escalab.mediappbackend.dto.FiltroConsultaDTO;
import com.escalab.mediappbackend.model.Consulta;

import java.util.List;

public interface IConsultaService extends ICRUD<Consulta> {
	
	Consulta registrarTransaccional(ConsultaListaExamenDTO dto);
	
	List<Consulta> buscar(FiltroConsultaDTO filtro);
	
	List<Consulta> buscarFecha(FiltroConsultaDTO filtro);
	
	List<ConsultaResumenDTO> listarResumen();
	
	byte[] generarReporte();
	

}
