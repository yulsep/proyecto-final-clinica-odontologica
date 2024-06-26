package dh.backend.proyecto_final_clinica_odontologica.service;



import dh.backend.proyecto_final_clinica_odontologica.entity.Odontologo;
import dh.backend.proyecto_final_clinica_odontologica.entity.Paciente;
import dh.backend.proyecto_final_clinica_odontologica.exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IPacienteService {

    Paciente registrarPaciente(Paciente paciente);

    Optional<Paciente> buscarPorId(Integer id);

    List<Paciente> buscarTodos();
    void actualizarPaciente(Paciente paciente);
    void eliminarPaciente(Integer id) throws ResourceNotFoundException;

    List<Paciente> buscarPorApellido(String apellido);
    List<Paciente> buscarPorDni(String dni);
    List<Paciente> buscarPorFechaIngreso(LocalDate fechaIngreso);
}
