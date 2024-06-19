package dh.backend.proyecto_final_clinica_odontologica.repository;

import dh.backend.proyecto_final_clinica_odontologica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOdontologoRepository  extends JpaRepository<Odontologo, Integer> {
    // Buscar odontologos por apellido
    @Query("Select o from Odontologo o where LOWER(o.apellido) = LOWER(:apellido)")
    List<Odontologo> buscarPorApellido(String apellido);

    // Buscar odontologos por nombre
    @Query("Select o from Odontologo o where LOWER(o.nombre) LIKE LOWER(CONCAT('%',:nombre,'%'))")
    List<Odontologo> findByNombreLike(@Param("nombre") String nombre);

    // Buscar odontologos por numero de matricula
    @Query("Select o from Odontologo o where LOWER(o.nroMatricula) LIKE LOWER(CONCAT('%',:nroMatricula,'%'))")
    List<Odontologo> findByNroMatricula (@Param("nroMatricula") String nroMatricula);

}
