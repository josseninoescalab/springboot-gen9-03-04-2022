package com.escalab.mediappbackend.dto;
import com.escalab.mediappbackend.model.Medico;
import com.escalab.mediappbackend.model.Paciente;

import org.springframework.hateoas.ResourceSupport;


public class ConsultaDTO extends ResourceSupport {
	
	private Integer idConsulta;
	private Medico medico;
	private Paciente paciente;
	
	public Integer getIdConsulta() {
		return idConsulta;
	}
	public void setIdConsulta(Integer idConsulta) {
		this.idConsulta = idConsulta;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
}
