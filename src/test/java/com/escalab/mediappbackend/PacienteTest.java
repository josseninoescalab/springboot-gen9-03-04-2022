package com.escalab.mediappbackend;

import com.escalab.mediappbackend.exception.ModeloNotFoundException;
import com.escalab.mediappbackend.model.Paciente;
import com.escalab.mediappbackend.repository.PacienteRepository;
import com.escalab.mediappbackend.service.PacienteService;
import com.escalab.mediappbackend.service.impl.PacienteServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner .class)
@SpringBootTest
public class PacienteTest {

    @Mock
    PacienteRepository pacienteRepository;

    @InjectMocks
    PacienteService pacienteService = new PacienteServiceImpl();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getPacienteOK(){
        Integer pacienteId = 4;
        Paciente pacienteReturn = new Paciente();
        pacienteReturn.setIdPaciente(4);
        pacienteReturn.setNombres("pruebas unitarias");
        pacienteReturn.setApellidos("Unit");
        when(pacienteRepository.findById(pacienteId)).thenReturn(Optional.of(pacienteReturn));
        Paciente paciente = pacienteService.leerPorId(pacienteId);
        assertEquals(paciente.getIdPaciente(), pacienteId);
    }

    @Test
    public void getPacienteNOK(){
        Integer pacienteId = null;
        Paciente pacienteReturn = new Paciente();
        pacienteReturn.setIdPaciente(4);
        pacienteReturn.setNombres("pruebas unitarias");
        pacienteReturn.setApellidos("Unit");
        when(pacienteRepository.findById(pacienteId)).thenReturn(Optional.ofNullable(pacienteReturn));
        Paciente paciente = pacienteService.leerPorId(pacienteId);
        assertEquals(paciente.getIdPaciente(), pacienteId);
    }

    @Test
    public void save(){
        Integer pacienteId = 4;
        Paciente pacienteReturn = new Paciente();
        pacienteReturn.setIdPaciente(4);
        pacienteReturn.setNombres("pruebas unitarias");
        pacienteReturn.setApellidos("Unit");
        pacienteReturn.setDireccion("Pueba tres esquinas");
        pacienteReturn.setDni("1-9");
        pacienteReturn.setEmail("pruebas@prueba.com");
        when(pacienteRepository.save(pacienteReturn)).thenReturn(pacienteReturn);
        Paciente paciente = pacienteService.registrar(pacienteReturn);
        assertEquals(paciente.getIdPaciente(), pacienteReturn.getIdPaciente());
    }
}
