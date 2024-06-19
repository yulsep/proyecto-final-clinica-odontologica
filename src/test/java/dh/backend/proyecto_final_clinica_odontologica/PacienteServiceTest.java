package dh.backend.proyecto_final_clinica_odontologica;

import dh.backend.proyecto_final_clinica_odontologica.entity.Domicilio;
import dh.backend.proyecto_final_clinica_odontologica.entity.Paciente;
import dh.backend.proyecto_final_clinica_odontologica.service.impl.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PacienteServiceTest {
    private static Logger LOGGER = LoggerFactory.getLogger(PacienteServiceTest.class);
    @Autowired
    private PacienteService pacienteService;
    private Paciente paciente;

    @BeforeEach
    public void setUp() {
        paciente = new Paciente();
        paciente.setNombre("Antonio");
        paciente.setApellido("Ramirez");
        paciente.setDni("1234567890");
        paciente.setFechaIngreso(LocalDate.of(2024,02,06));
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Calle 1");
        domicilio.setNumero(579);
        domicilio.setLocalidad("Guayabal");
        domicilio.setProvincia("Antioquia");
        paciente.setDomicilio(domicilio);
    }

    @Test
    @DisplayName("Test de creacion de paciente")
    void testCreacionPaciente() {
        Paciente pacienteDeLaDB = pacienteService.registrarPaciente(paciente);

        assertNotNull(pacienteDeLaDB);
    }

    @Test
    @DisplayName("Test de buscar paciente por id")
    void testBuscarPacientePorId() {
        Integer id = 1;
        Optional<Paciente> pacienteEncontrado = pacienteService.buscarPorId(id);
        Paciente paciente1 = pacienteEncontrado.get();

        assertEquals(id, paciente1.getId());
    }

    @Test
    @DisplayName("Test buscar todos los pacientes")
    void testBusquedaTodos() {

        List<Paciente> pacientes = pacienteService.buscarTodos();

        assertTrue(pacientes.size()!=0);

    }
}
