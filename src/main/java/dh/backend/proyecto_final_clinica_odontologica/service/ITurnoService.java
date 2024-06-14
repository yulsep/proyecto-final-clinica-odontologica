package dh.backend.proyecto_final_clinica_odontologica.service;


import dh.backend.proyecto_final_clinica_odontologica.entity.Turno;
import dh.backend.proyecto_final_clinica_odontologica.exception.BadRequestException;
import dh.backend.proyecto_final_clinica_odontologica.exception.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {
    Turno registrar(Turno turnoRequestDto) throws BadRequestException;

    Turno buscarPorId(Integer id);

    List<Turno> buscarTodos();
    void actualizarTurno(Integer id, Turno turnoRequestDto) throws BadRequestException;
    void eliminarTurno(Integer id) throws ResourceNotFoundException;
}
