package dh.backend.proyecto_final_clinica_odontologica.controller;

import dh.backend.proyecto_final_clinica_odontologica.entity.Turno;
import dh.backend.proyecto_final_clinica_odontologica.exception.BadRequestException;
import dh.backend.proyecto_final_clinica_odontologica.exception.ResourceNotFoundException;
import dh.backend.proyecto_final_clinica_odontologica.service.ITurnoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    private static final Logger logger =  LoggerFactory.getLogger(TurnoController.class);
    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping
        public ResponseEntity<Turno> agregarTurno(@RequestBody Turno turno) throws BadRequestException {
        logger.info("Agregando Turno");
        Turno turnoADevolver = turnoService.registrar(turno);
        return ResponseEntity.status(HttpStatus.CREATED).body(turnoADevolver);

    }
    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodosTurnos(){
        logger.info("Buscando Todos");
        return ResponseEntity.ok(turnoService.buscarTodos());
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> modificarTurno(@PathVariable Integer id, @RequestBody Turno turno) throws BadRequestException {
        logger.info("Modificando Turno");
        turnoService.actualizarTurno(id, turno);
        return ResponseEntity.ok("{\"message\": \"turno modificado\"}");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Integer id) throws ResourceNotFoundException {
        logger.info("Eliminando Turno");
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("{\"message\": \"turno eliminado\"}");
    }

}
