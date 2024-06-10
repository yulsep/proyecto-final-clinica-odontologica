package dh.backend.proyecto_final_clinica_odontologica.service;


import dh.backend.proyecto_final_clinica_odontologica.entity.Turno;

import java.util.List;

public interface ITurnoService {
    Turno registrar(Turno turnoRequestDto);

    Turno buscarPorId(Integer id);

    List<Turno> buscarTodos();
    void actualizarTurno(Integer id, Turno turnoRequestDto);
    void eliminarTurno(Integer id);
}
