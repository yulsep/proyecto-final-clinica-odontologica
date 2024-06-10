package dh.backend.proyecto_final_clinica_odontologica.service.impl;

import dh.backend.proyecto_final_clinica_odontologica.entity.Odontologo;
import dh.backend.proyecto_final_clinica_odontologica.entity.Paciente;
import dh.backend.proyecto_final_clinica_odontologica.entity.Turno;
import dh.backend.proyecto_final_clinica_odontologica.repository.IOdontologoRepository;
import dh.backend.proyecto_final_clinica_odontologica.repository.IPacienteRepository;
import dh.backend.proyecto_final_clinica_odontologica.repository.ITurnoRepository;
import dh.backend.proyecto_final_clinica_odontologica.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {
    private IOdontologoRepository odontologoRepository;
    private IPacienteRepository pacienteRepository;
    private ITurnoRepository turnoRepository;

    public TurnoService(IOdontologoRepository odontologoRepository, IPacienteRepository pacienteRepository, ITurnoRepository turnoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.pacienteRepository = pacienteRepository;
        this.turnoRepository = turnoRepository;
    }

    @Override
    public Turno registrar(Turno turno) {
        Optional<Paciente> paciente = pacienteRepository.findById(turno.getPaciente().getId());
        Optional<Odontologo> odontologo = odontologoRepository.findById(turno.getOdontologo().getId());
        if(paciente.isPresent() && odontologo.isPresent()){
            turno.setPaciente(paciente.get());
            turno.setOdontologo(odontologo.get());
            turno.setFecha(turno.getFecha());
            return turnoRepository.save(turno);
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
    public void actualizarTurno(Integer id, Turno turnoRequest) {
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
        }
    }

    @Override
    public void eliminarTurno(Integer id) {
        turnoRepository.deleteById(id);
    }

}
