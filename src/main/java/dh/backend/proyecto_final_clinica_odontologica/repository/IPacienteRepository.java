package dh.backend.proyecto_final_clinica_odontologica.repository;

import dh.backend.proyecto_final_clinica_odontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {
}
