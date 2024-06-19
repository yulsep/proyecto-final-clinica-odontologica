package dh.backend.proyecto_final_clinica_odontologica.service;


import dh.backend.proyecto_final_clinica_odontologica.entity.Turno;
import dh.backend.proyecto_final_clinica_odontologica.exception.BadRequestException;
import dh.backend.proyecto_final_clinica_odontologica.exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface ITurnoService {
    Turno registrar(Turno turno) throws BadRequestException;

    Turno buscarPorId(Integer id);

    List<Turno> buscarTodos();
    void actualizarTurno(Integer id, Turno turno) throws BadRequestException;
    void eliminarTurno(Integer id) throws ResourceNotFoundException;

    // HQL
    List<Turno> buscarTurnoEntreFechas(LocalDate startDate, LocalDate endDate);
}
