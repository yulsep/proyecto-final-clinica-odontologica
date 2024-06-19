package dh.backend.proyecto_final_clinica_odontologica.repository;

import dh.backend.proyecto_final_clinica_odontologica.entity.Odontologo;
import dh.backend.proyecto_final_clinica_odontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {
    // Buscar pacientes por apellido
    @Query("Select o from Paciente o where LOWER(o.apellido) = LOWER(:apellido)")
    List<Paciente> findAllByApellido(String apellido);

    // Buscar pacientes por DNI
    @Query("Select o from Paciente o where LOWER(o.dni) = LOWER(:dni)")
    List<Paciente> findAllByDni(String dni);

    // Buscar pacientes que ingresaron después de una fecha específica
    @Query("SELECT p FROM Paciente p WHERE p.fechaIngreso > :fecha")
    List<Paciente> findAllByFechaIngresoAfter(@Param("fecha") LocalDate fecha);

}
