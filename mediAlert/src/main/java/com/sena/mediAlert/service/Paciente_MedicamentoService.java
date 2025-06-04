package com.sena.mediAlert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.mediAlert.DTO.Paciente_MedicamentoDTO;
import com.sena.mediAlert.DTO.responseDTO;
import com.sena.mediAlert.model.EstadoRecordatorio;
import com.sena.mediAlert.model.Paciente_Medicamento;
import com.sena.mediAlert.repository.IPaciente_Medicamento;

@Service
public class Paciente_MedicamentoService {
    @Autowired
    private IPaciente_Medicamento data;

    public List<Paciente_Medicamento> findAll() {
        return data.findAll();
    }

    public Optional<Paciente_Medicamento> findById(int id) {
        return data.findById(id);
    }

    public List<Paciente_Medicamento> findAllByPacienteId(int pacienteid) {
        return data.findAllByPacienteId(pacienteid);
    }

    public List<Paciente_Medicamento> findAllByMedicamentoId(int medicamentoid) {
        return data.findAllByMedicamentoId(medicamentoid);
    }

    public responseDTO deleteByPacienteId(int pacienteid) {
        List<Paciente_Medicamento> pacienteMedicamento = findAllByPacienteId(pacienteid);
        if (pacienteMedicamento.isEmpty()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.OK.toString(),
                "El paciente no existe"
            );
            return respuesta;
        }
        data.deleteByPacienteId(pacienteid);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "El paciente ha sido eliminado"
        );
        return respuesta;
    }

    public responseDTO deleteByMedicamentoId(int medicamentoid) {
        List<Paciente_Medicamento> pacienteMedicamento = findAllByMedicamentoId(medicamentoid);
        if (pacienteMedicamento.isEmpty()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.OK.toString(),
                "El medicamento no existe"
            );
            return respuesta;
        }
        data.deleteByMedicamentoId(medicamentoid);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "El medicamento ha sido eliminado"
        );
        return respuesta;
    }

    public responseDTO save(Paciente_MedicamentoDTO Paciente_MedicamentoDTO) {
        if (Paciente_MedicamentoDTO.getPacienteid() == null || Paciente_MedicamentoDTO.getMedicamentoid() == null) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El paciente o el medicamento no puede estar vacio"
            );
            return respuesta;
        }
        if(Paciente_MedicamentoDTO.getDosis().length() < 1 || Paciente_MedicamentoDTO.getDosis().length() > 100) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "La dosis del medicamento no puede estar vacia"
            );
            return respuesta;
        }
        if (Paciente_MedicamentoDTO.getHora() == null) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "La hora del medicamento no puede estar vacia"
            );
            return respuesta;
        }
        Paciente_Medicamento pacienteMedicamentoRegister = convertToModel(Paciente_MedicamentoDTO);
        pacienteMedicamentoRegister.setEstado(EstadoRecordatorio.SINENVIAR);
        data.save(pacienteMedicamentoRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "El medicamento ha sido registrado"
        );
        return respuesta;
    }

    public responseDTO updatePacienteMedicamento(int id, Paciente_MedicamentoDTO DTO) {
        if (DTO.getPacienteid() == null || DTO.getMedicamentoid() == null) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El paciente o el medicamento no puede estar vacio"
            );
            return respuesta;
        }
        if (DTO.getDosis().length() < 1 || DTO.getDosis().length() > 100) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "La dosis del medicamento no puede estar vacia"
            );
            return respuesta;
        }
        if (DTO.getHora() == null) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "La hora del medicamento no puede estar vacia"
            );
            return respuesta;
        }
        if (DTO.getEstado() == null) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El estado del medicamento no puede estar vacia"
            );
            return respuesta;
        }
        Paciente_Medicamento existingPaciente_Medicamento = data.findById(id).get();
        existingPaciente_Medicamento.setPacienteid(DTO.getPacienteid());
        existingPaciente_Medicamento.setMedicamentoid(DTO.getMedicamentoid());
        existingPaciente_Medicamento.setDosis(DTO.getDosis());
        existingPaciente_Medicamento.setHora(DTO.getHora());
        data.save(existingPaciente_Medicamento);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "El medicamento ha sido actualizado"
        );
        return respuesta;  
    }

    public Paciente_MedicamentoDTO convertToDTO(Paciente_Medicamento pacienteMedicamento) {
        Paciente_MedicamentoDTO pacienteMedicamentoDTO = new Paciente_MedicamentoDTO(
            pacienteMedicamento.getPacienteid(),
            pacienteMedicamento.getMedicamentoid(),
            pacienteMedicamento.getDosis(),
            pacienteMedicamento.getHora(),
            pacienteMedicamento.getEstado()
        );
        return pacienteMedicamentoDTO;
    }

    public Paciente_Medicamento convertToModel(Paciente_MedicamentoDTO pacienteMedicamentoDTO) {
        Paciente_Medicamento pacienteMedicamento = new Paciente_Medicamento(
            0,
            pacienteMedicamentoDTO.getPacienteid(),
            pacienteMedicamentoDTO.getMedicamentoid(),
            pacienteMedicamentoDTO.getDosis(),
            pacienteMedicamentoDTO.getHora(),
            pacienteMedicamentoDTO.getEstado()
        );
        return pacienteMedicamento;
    }
}
