package dh.backend.proyecto_final_clinica_odontologica.service.impl;

import dh.backend.proyecto_final_clinica_odontologica.entity.Odontologo;
import dh.backend.proyecto_final_clinica_odontologica.entity.Paciente;
import dh.backend.proyecto_final_clinica_odontologica.entity.Turno;
import dh.backend.proyecto_final_clinica_odontologica.exception.BadRequestException;
import dh.backend.proyecto_final_clinica_odontologica.exception.ResourceNotFoundException;
import dh.backend.proyecto_final_clinica_odontologica.repository.IOdontologoRepository;
import dh.backend.proyecto_final_clinica_odontologica.repository.IPacienteRepository;
import dh.backend.proyecto_final_clinica_odontologica.repository.ITurnoRepository;
import dh.backend.proyecto_final_clinica_odontologica.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {
    private static final Logger logger =  LoggerFactory.getLogger(OdontologoService.class);
    private IOdontologoRepository odontologoRepository;
    private IPacienteRepository pacienteRepository;
    private ITurnoRepository turnoRepository;

    public TurnoService(IOdontologoRepository odontologoRepository, IPacienteRepository pacienteRepository, ITurnoRepository turnoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.pacienteRepository = pacienteRepository;
        this.turnoRepository = turnoRepository;
    }

    @Override
    public Turno registrar(Turno turno) throws BadRequestException {
        Optional<Paciente> paciente = pacienteRepository.findById(turno.getPaciente().getId());
        Optional<Odontologo> odontologo = odontologoRepository.findById(turno.getOdontologo().getId());
        if(paciente.isPresent() && odontologo.isPresent()){
            turno.setPaciente(paciente.get());
            turno.setOdontologo(odontologo.get());
            turno.setFecha(turno.getFecha());
            return turnoRepository.save(turno);
        } else {
            if (!paciente.isPresent()) {
                logger.error("Paciente no encontrado");
                throw new BadRequestException("{\"message\": \"El paciente con ID " + turno.getPaciente().getId() + " no se encuentra.\"}");
            }
            if (!odontologo.isPresent()) {
                logger.error("Odontologo no encontrado");
                throw new BadRequestException("{\"message\": \"El odontólogo con ID " + turno.getOdontologo().getId() + " no se encuentra.\"}");
            }
        }
        return null;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        if(turnoOptional.isPresent()){
            Turno turnoEncontrado = turnoOptional.get();
            return turnoEncontrado;
        }
        return null;
    }

    @Override
    public List<Turno> buscarTodos() {
        List<Turno> turnos = turnoRepository.findAll();
        List<Turno> turnosADevolver = new ArrayList<>();
        for(Turno turno: turnos){
            turnosADevolver.add(turno);
        }
        return turnosADevolver;
    }

    @Override
    public void actualizarTurno(Integer id, Turno turnoRequest) throws BadRequestException {
        Optional<Paciente> paciente = pacienteRepository.findById(turnoRequest.getPaciente().getId());
        Optional<Odontologo> odontologo = odontologoRepository.findById(turnoRequest.getOdontologo().getId());
        Optional<Turno> turno = turnoRepository.findById(id);
        Turno turnoAModificar = new Turno();
        if(paciente.isPresent() && odontologo.isPresent() && turno.isPresent()){
            turnoAModificar.setId(id);
            turnoAModificar.setOdontologo(odontologo.get());
            turnoAModificar.setPaciente(paciente.get());
            turnoAModificar.setFecha(turnoRequest.getFecha());
            turnoRepository.save(turnoAModificar);
        } else {
            if (!paciente.isPresent()) {
                logger.error("Paciente no encontrado");
                throw new BadRequestException("{\"message\": \"El paciente con ID " + turnoRequest.getPaciente().getId() + " no se encuentra.\"}");
            }
            if (!odontologo.isPresent()) {
                logger.error("Odontologo no encontrado");
                throw new BadRequestException("{\"message\": \"El odontólogo con ID " + turnoRequest.getOdontologo().getId() + " no se encuentra.\"}");
            }
            if (!turno.isPresent()) {
                logger.error("Turno no encontrado");
                throw new BadRequestException("{\"message\": \"El turno con ID " + id + " no se encuentra.\"}");
            }
        }
    }

    @Override
    public void eliminarTurno(Integer id) throws ResourceNotFoundException {
        Optional<Turno> turnoOptional = Optional.ofNullable(buscarPorId(id));

        if(turnoOptional.isPresent()){
            turnoRepository.deleteById(id);
        }
        else throw new ResourceNotFoundException("{\"message\": \"turno no encontrado\"}");
    }

    @Override
    public List<Turno> buscarTurnoEntreFechas(LocalDate startDate, LocalDate endDate) {
        return List.of();
    }

}
