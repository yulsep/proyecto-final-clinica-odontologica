package dh.backend.proyecto_final_clinica_odontologica.repository;

import dh.backend.proyecto_final_clinica_odontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ITurnoRepository extends JpaRepository<Turno, Integer> {
    //Buscar turnos entre dos fechas
    @Query("Select t from Turno t where t.fecha BETWEEN :startDate and :endDate")
    List<Turno> buscarTurnoEntreFechas(@Param("startDate") LocalDate startDate, @Param("endDate")LocalDate endDate);
}
