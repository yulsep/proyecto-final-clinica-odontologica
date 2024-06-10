package dh.backend.proyecto_final_clinica_odontologica.service;

import dh.backend.proyecto_final_clinica_odontologica.entity.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo agregarOdontologo(Odontologo odontologo);

    Optional<Odontologo> buscarUnOdontologo(Integer id);
    List<Odontologo> buscarTodosOdontologos();

    void modificarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(Integer id);
}
