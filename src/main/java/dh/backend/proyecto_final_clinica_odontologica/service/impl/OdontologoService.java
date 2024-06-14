package dh.backend.proyecto_final_clinica_odontologica.service.impl;



import dh.backend.proyecto_final_clinica_odontologica.entity.Odontologo;
import dh.backend.proyecto_final_clinica_odontologica.exception.ResourceNotFoundException;
import dh.backend.proyecto_final_clinica_odontologica.repository.IOdontologoRepository;
import dh.backend.proyecto_final_clinica_odontologica.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {

    private IOdontologoRepository odontologoRepository;

    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo agregarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }

    public Optional<Odontologo> buscarUnOdontologo(Integer id){
        return odontologoRepository.findById(id);
    }
    public List<Odontologo> buscarTodosOdontologos(){
        return odontologoRepository.findAll();
    }

    @Override
    public void modificarOdontologo(Odontologo odontologo) {
        odontologoRepository.save(odontologo);
    }

    @Override
    public void eliminarOdontologo(Integer id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoOptional = buscarUnOdontologo(id);
        if(odontologoOptional.isPresent()){
            odontologoRepository.deleteById(id);
        }
        else
            throw new ResourceNotFoundException("{\"message\": \"odontologo no encontrado\"}");

    }
}
