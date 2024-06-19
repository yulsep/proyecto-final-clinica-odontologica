package dh.backend.proyecto_final_clinica_odontologica;

import dh.backend.proyecto_final_clinica_odontologica.entity.Odontologo;
import dh.backend.proyecto_final_clinica_odontologica.entity.Paciente;
import dh.backend.proyecto_final_clinica_odontologica.entity.Turno;
import dh.backend.proyecto_final_clinica_odontologica.exception.BadRequestException;
import dh.backend.proyecto_final_clinica_odontologica.exception.ResourceNotFoundException;
import dh.backend.proyecto_final_clinica_odontologica.repository.IOdontologoRepository;
import dh.backend.proyecto_final_clinica_odontologica.repository.IPacienteRepository;
import dh.backend.proyecto_final_clinica_odontologica.service.ITurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TurnoServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TurnoServiceTest.class);

    @Autowired
    private ITurnoService turnoService;

    @Autowired
    private IOdontologoRepository odontologoRepository;

    @Autowired
    private IPacienteRepository pacienteRepository;

    private Paciente paciente;
    private Odontologo odontologo;

    @BeforeEach
    public void setUp() {
        paciente = new Paciente();
        paciente.setNombre("Juan");
        paciente.setApellido("Perez");
        paciente.setDni("1234567890");
        paciente.setFechaIngreso(LocalDate.of(2024, 02, 06));
        paciente = pacienteRepository.save(paciente);

        odontologo = new Odontologo();
        odontologo.setNroMatricula("12345");
        odontologo.setNombre("Maria");
        odontologo.setApellido("Gonzalez");
        odontologo = odontologoRepository.save(odontologo);
    }

    @Test
    @DisplayName("Test de registro de turno")
    public void testRegistrarTurno() throws BadRequestException {
        Turno turno = new Turno();
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFecha(LocalDate.now().plusDays(7));

        Turno turnoGuardado = turnoService.registrar(turno);

        assertNotNull(turnoGuardado.getId());
        LOGGER.info("Turno registrado con id: {}", turnoGuardado.getId());
    }

    @Test
    @DisplayName("Test de buscar Turno por id")
    public void testBuscarTurnoPorId() throws ResourceNotFoundException, BadRequestException {
        Turno turno = new Turno();
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFecha(LocalDate.now().plusDays(7));
        turno.setId(1);
        turno = turnoService.registrar(turno);

        Turno turnoEncontrado = turnoService.buscarPorId(turno.getId());

        assertNotNull(turnoEncontrado);
        LOGGER.info("Turno encontrado con id: {}", turnoEncontrado.getId());
    }

    @Test
    @DisplayName("Test buscar todos los turnos")
    public void testBuscarTodosTurnos() {

        List<Turno> turnos = turnoService.buscarTodos();

        assertTrue(turnos.size() > 0);
    }

}
