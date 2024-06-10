package dh.backend.proyecto_final_clinica_odontologica.controller;

import dh.backend.proyecto_final_clinica_odontologica.entity.Turno;
import dh.backend.proyecto_final_clinica_odontologica.service.ITurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping
        public ResponseEntity<Turno> agregarTurno(@RequestBody Turno turno){
        Turno turnoADevolver = turnoService.registrar(turno);
        if(turnoADevolver==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(turnoADevolver);
        }
    }
    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodosTurnos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> modificarTurno(@PathVariable Integer id, @RequestBody Turno turno){
        turnoService.actualizarTurno(id, turno);
        return ResponseEntity.ok("Turno modificado");
    }

}
