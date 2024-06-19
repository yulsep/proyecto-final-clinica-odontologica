package dh.backend.proyecto_final_clinica_odontologica;

import dh.backend.proyecto_final_clinica_odontologica.entity.Odontologo;
import dh.backend.proyecto_final_clinica_odontologica.service.impl.OdontologoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class OdontologoServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoServiceTest.class);

    @Autowired
    private OdontologoService odontologoService;

    private Odontologo odontologo;

    @BeforeEach
    public void setUp() {
        odontologo = new Odontologo();
        odontologo.setNroMatricula("12345");
        odontologo.setNombre("Juan");
        odontologo.setApellido("Perez");
    }

    @Test
    @DisplayName("Test de creacion de odontologo")
    public void testCrearOdontologo() {
        Odontologo odontologoGuardado = odontologoService.agregarOdontologo(odontologo);

        assertNotNull(odontologoGuardado.getId());
        LOGGER.info("Odontologo creado con id: {}", odontologoGuardado.getId());
    }

    @Test
    @DisplayName("Test de buscar odontologo por id")
    public void testBuscarOdontologoPorId() {
        Integer id = 1;
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarUnOdontologo(id);

        assertTrue(odontologoEncontrado.isPresent());
        LOGGER.info("Odontologo encontrado con id: {}", id);
    }

    @Test
    @DisplayName("Test buscar todos los odontologos")
    public void testBuscarTodosOdontologos() {
        List<Odontologo> odontologos = odontologoService.buscarTodosOdontologos();

        assertTrue(odontologos.size() > 0, "No hay odontologos guardados");
        LOGGER.info("Odontologos encontrados: {}", odontologos.size());
    }

}
