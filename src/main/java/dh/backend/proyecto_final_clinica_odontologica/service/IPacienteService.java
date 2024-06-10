package dh.backend.proyecto_final_clinica_odontologica.service;



import dh.backend.proyecto_final_clinica_odontologica.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {

    Paciente registrarPaciente(Paciente paciente);

    Optional<Paciente> buscarPorId(Integer id);

    List<Paciente> buscarTodos();
    void actualizarPaciente(Paciente paciente);
    void eliminarPaciente(Integer id);
}
