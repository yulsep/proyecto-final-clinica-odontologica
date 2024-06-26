package dh.backend.proyecto_final_clinica_odontologica.service.impl;


import dh.backend.proyecto_final_clinica_odontologica.entity.Paciente;
import dh.backend.proyecto_final_clinica_odontologica.exception.ResourceNotFoundException;
import dh.backend.proyecto_final_clinica_odontologica.repository.IPacienteRepository;
import dh.backend.proyecto_final_clinica_odontologica.service.IPacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    private static final Logger logger =  LoggerFactory.getLogger(PacienteService.class);

    private IPacienteRepository pacienteRepository;

    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente registrarPaciente(Paciente paciente){

        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscarPorId(Integer id){
        return pacienteRepository.findById(id);
    }

    public List<Paciente> buscarTodos(){
        return pacienteRepository.findAll();
    }

    @Override
    public void actualizarPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    @Override
    public void eliminarPaciente(Integer id) throws ResourceNotFoundException {

        Optional<Paciente> pacienteOptional = buscarPorId(id);
        if (pacienteOptional.isPresent()) {
            pacienteRepository.deleteById(id);
        }
        else throw new ResourceNotFoundException("{\"message\": \"paciente no encontrado\"}");
    }

    @Override
    public List<Paciente> buscarPorApellido(String apellido) {
        return pacienteRepository.findAllByApellido(apellido);
    }

    @Override
    public List<Paciente> buscarPorDni(String dni) {
        return pacienteRepository.findAllByDni(dni);
    }

    @Override
    public List<Paciente> buscarPorFechaIngreso(LocalDate fechaIngreso) {
        return pacienteRepository.findAllByFechaIngresoAfter(fechaIngreso);
    }
}
