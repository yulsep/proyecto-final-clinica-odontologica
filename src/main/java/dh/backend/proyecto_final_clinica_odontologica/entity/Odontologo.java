package dh.backend.proyecto_final_clinica_odontologica.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="odontologos")

public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;
    private String nroMatricula;
    private String nombre;
    private String apellido;
}
