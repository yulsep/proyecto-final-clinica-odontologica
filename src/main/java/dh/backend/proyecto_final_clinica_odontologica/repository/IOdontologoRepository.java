package dh.backend.proyecto_final_clinica_odontologica.repository;

import dh.backend.proyecto_final_clinica_odontologica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOdontologoRepository  extends JpaRepository<Odontologo, Integer> {
}
