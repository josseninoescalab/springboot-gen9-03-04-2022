package com.escalab.mediappbackend.controller;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.escalab.mediappbackend.dto.ConsultaDTO;
import com.escalab.mediappbackend.dto.RespuestaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.escalab.mediappbackend.dto.ConsultaListaExamenDTO;
import com.escalab.mediappbackend.dto.ConsultaResumenDTO;
import com.escalab.mediappbackend.dto.FiltroConsultaDTO;
import com.escalab.mediappbackend.exception.ModeloNotFoundException;
import com.escalab.mediappbackend.model.Archivo;
import com.escalab.mediappbackend.model.Consulta;
import com.escalab.mediappbackend.service.IArchivoService;
import com.escalab.mediappbackend.service.IConsultaService;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

	@Autowired
	private IConsultaService service;

	@Autowired
	private IArchivoService serviceArchivo;

	@GetMapping
	public ResponseEntity<List<Consulta>> listar(){
		List<Consulta> lista = service.listar();
		return new ResponseEntity<List<Consulta>>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Consulta> listarPorId(@PathVariable("id") Integer id){
		Consulta obj = service.leerPorId(id);
		if(obj.getIdConsulta() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		return new ResponseEntity<Consulta>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/hateoas", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ConsultaDTO> listarHateoas() {
		List<Consulta> consultas = new ArrayList<>();
		List<ConsultaDTO> consultasDTO = new ArrayList<>();
		consultas = service.listar();
		
		for (Consulta c : consultas) {
			ConsultaDTO d = new ConsultaDTO();
			d.setIdConsulta(c.getIdConsulta());
			//d.setMedico(c.getMedico());
			//d.setPaciente(c.getPaciente());

			// localhost:8080/consultas/1
			ControllerLinkBuilder linkTo = linkTo(methodOn(ConsultaController.class).listarPorId((c.getIdConsulta())));
			d.add(linkTo.withSelfRel());
			consultasDTO.add(d);
			
			// localhost:0880/paciente/1
			ControllerLinkBuilder linkTo1 = linkTo(methodOn(PacienteController.class).findById((c.getPaciente().getIdPaciente())));
			d.add(linkTo1.withSelfRel());
			consultasDTO.add(d);

			ControllerLinkBuilder linkTo2 = linkTo(methodOn(MedicoController.class).findById((c.getMedico().getIdMedico())));
			d.add(linkTo2.withSelfRel());

			// localhost:0880/especialidad/1
			ControllerLinkBuilder linkEspecialidad = linkTo(methodOn(EspecialidadController.class).listarPorId(c.getEspecialidad().getIdEspecialidad()));
			d.add(linkEspecialidad.withSelfRel());
			consultasDTO.add(d);
		}
		return consultasDTO;
	}

	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody ConsultaListaExamenDTO consultaDTO) {
		Consulta obj = service.registrarTransaccional(consultaDTO);
		//consultas/4
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdConsulta()).toUri();
		return ResponseEntity.created(location).build();
	}


	@PutMapping
	public ResponseEntity<Consulta> modificar(@Valid @RequestBody Consulta consulta) {
		Consulta obj = service.modificar(consulta);
		return new ResponseEntity<Consulta>(obj, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
		Consulta obj = service.leerPorId(id);
		if(obj.getIdConsulta() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@PostMapping("/buscar")
	public ResponseEntity<List<Consulta>> buscar(@RequestBody FiltroConsultaDTO filtro) {
		List<Consulta> consultas = new ArrayList<>();

		if (filtro != null) {
			if (filtro.getFechaConsulta() != null) {
				consultas = service.buscarFecha(filtro);
			} else {
				consultas = service.buscar(filtro);
			}
		}
		return new ResponseEntity<List<Consulta>>(consultas, HttpStatus.OK);
	}

	@GetMapping(value = "/listarResumen")
	public ResponseEntity<List<ConsultaResumenDTO>> listarResumen() {
		List<ConsultaResumenDTO> consultas = new ArrayList<>();
		consultas = service.listarResumen();
		return new ResponseEntity<List<ConsultaResumenDTO>>(consultas, HttpStatus.OK);
	}

	@GetMapping(value = "/generarReporte", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> generarReporte(){
		byte[] data = null;
		data = service.generarReporte();
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}

	@GetMapping(value = "/generarReporte/pdf")
	public @ResponseBody
	ResponseEntity<RespuestaDTO> generarReportePDF(){
		byte[] data = null;
		data = service.generarReporte();
		String document = Base64.getEncoder().encodeToString(data);
		return new ResponseEntity<>(new RespuestaDTO(document), HttpStatus.OK);
	}

	@PostMapping(value = "/guardarArchivo", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Integer> guardarArchivo(@RequestParam("adjunto") MultipartFile file) throws IOException{
		int rpta = 0;
		Archivo ar = new Archivo();
		ar.setFiletype(file.getContentType());
		ar.setFilename(file.getName());
		ar.setValue(file.getBytes());

		rpta = serviceArchivo.guardar(ar);

		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}

	@GetMapping(value = "/leerArchivo/{idArchivo}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> leerArchivo(@PathVariable("idArchivo") Integer idArchivo) throws IOException {

		byte[] arr = serviceArchivo.leerArchivo(idArchivo);

		return new ResponseEntity<byte[]>(arr, HttpStatus.OK);
	}
}

