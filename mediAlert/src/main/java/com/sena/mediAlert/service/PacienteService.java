package com.sena.mediAlert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.mediAlert.DTO.PacienteDTO;
import com.sena.mediAlert.DTO.responseDTO;
import com.sena.mediAlert.model.Paciente;
import com.sena.mediAlert.repository.IPaciente;

@Service
public class PacienteService {
    @Autowired
    private IPaciente data;

    public List<Paciente> findAll() {
        return data.findAll();
    }

    public Optional<Paciente> findById(int id) {
        return data.findById(id);
    }

    public responseDTO deletePaciente(int id) {
        if (!findById(id).isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.OK.toString(),
                "El paciente no existe"
            );
            return respuesta;
        }
        data.deleteById(id);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "El paciente ha sido eliminado"
        );
        return respuesta;
    }

    public responseDTO save(PacienteDTO PacienteDTO) {
        if (PacienteDTO.getNombre().length() < 1 || PacienteDTO.getNombre().length() > 200) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El nombre del paciente no puede estar vacio"
            );
            return respuesta;
        }
        if (PacienteDTO.getCorreo().length() < 1 || PacienteDTO.getCorreo().length() > 255) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El correo del paciente no puede estar vacio"
            );
            return respuesta;            
        }
        if (PacienteDTO.getFecha_nacimiento() == null) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "La fecha de nacimiento del paciente no puede estar vacia"
            );
            return respuesta;
        }
        Paciente pacienteRegister = convertToModel(PacienteDTO);
        data.save(pacienteRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "El paciente ha sido registrado"
        );
        return respuesta;
    }

    public responseDTO updatePaciente(int id, PacienteDTO DTO) {
        Optional<Paciente> pacienteOpt = findById(id);
        if (!pacienteOpt.isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(),
                "El paciente no existe"
            );
            return respuesta;
        }
        Paciente existingPaciente = pacienteOpt.get();
        existingPaciente.setNombre(DTO.getNombre());
        existingPaciente.setCorreo(DTO.getCorreo());
        existingPaciente.setFecha_nacimiento(DTO.getFecha_nacimiento());
        data.save(existingPaciente);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "El paciente ha sido actualizado"
        );
        return respuesta;
    }

    public PacienteDTO convertToDTO(Paciente paciente) {
        PacienteDTO pacienteDTO = new PacienteDTO(
            paciente.getNombre(),
            paciente.getFecha_nacimiento(),
            paciente.getCorreo()
        );
        return pacienteDTO;
    }

    public Paciente convertToModel(PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente(
            0,
            pacienteDTO.getNombre(),
            pacienteDTO.getFecha_nacimiento(),
            pacienteDTO.getCorreo()
        );
        return paciente;
    }
}
