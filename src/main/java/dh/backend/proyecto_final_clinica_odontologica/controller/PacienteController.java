package dh.backend.proyecto_final_clinica_odontologica.controller;

import dh.backend.proyecto_final_clinica_odontologica.entity.Odontologo;
import dh.backend.proyecto_final_clinica_odontologica.entity.Paciente;
import dh.backend.proyecto_final_clinica_odontologica.exception.ResourceNotFoundException;
import dh.backend.proyecto_final_clinica_odontologica.service.IPacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    private static final Logger logger =  LoggerFactory.getLogger(PacienteController.class);
    public IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Paciente>  registrarPaciente(@RequestBody Paciente paciente){
        logger.info("Registrando Paciente");
        Paciente pacienteARetornar = pacienteService.registrarPaciente(paciente);
        if(pacienteARetornar==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(pacienteARetornar);
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>>  buscarTodos(){

        logger.info("Buscando Todos");
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Integer id){
        logger.info("Buscando Paciente por ID");
        Optional<Paciente> paciente = pacienteService.buscarPorId(id);
        if(paciente.isPresent()){
            return ResponseEntity.ok(paciente.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping
    public ResponseEntity<String>  actualizarPaciente(@RequestBody Paciente paciente){
        logger.info("Actualizando Paciente");
        pacienteService.actualizarPaciente(paciente);
        return  ResponseEntity.ok("{\"message\": \"paciente no actualizado\"}");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>  borrarPaciente(@PathVariable Integer id) throws ResourceNotFoundException {
        logger.info("Borrando Paciente");
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("{\"message\": \"paciente eliminado\"}");
    }

    @GetMapping("/apellido/{apellido}")
    public ResponseEntity<List<Paciente>> buscarPorApellido(@PathVariable String apellido){
        List<Paciente> listaPacientes =pacienteService.buscarPorApellido(apellido);
        if(listaPacientes.size()>0){
            return ResponseEntity.ok(listaPacientes);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<List<Paciente>> buscarPorDni(@PathVariable String dni){
        return ResponseEntity.ok(pacienteService.buscarPorDni(dni));
    }

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<Paciente>> buscarPorFechaIngreso(@RequestParam String fecha){
        LocalDate fechaIngreso = LocalDate.parse(fecha, formatter);
        return ResponseEntity.ok(pacienteService.buscarPorFechaIngreso(fechaIngreso));
    }

}
