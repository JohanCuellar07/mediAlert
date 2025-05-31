package com.sena.mediAlert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.mediAlert.DTO.MedicamentoDTO;
import com.sena.mediAlert.DTO.responseDTO;
import com.sena.mediAlert.model.Medicamento;
import com.sena.mediAlert.repository.IMedicamento;

@Service
public class MedicamentoService {
    @Autowired
    private IMedicamento data;

    public List<Medicamento> findAll() {
        return data.findAll();
    }

    public Optional<Medicamento> findById(int id) {
        return data.findById(id);
    }

    public responseDTO deleteMedicamento(int id) {
        if (!findById(id).isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.OK.toString(),
                "El medicamento no existe"
            );
            return respuesta;
        }
        data.deleteById(id);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "El medicamento ha sido eliminado"
        );
        return respuesta;
    }

    public responseDTO save(MedicamentoDTO MedicamentoDTO) {
        if (MedicamentoDTO.getNombre().length() < 1 || MedicamentoDTO.getNombre().length() > 100) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El nombre del medicamento no puede estar vacio"
            );
            return respuesta;
        }
        Medicamento medicamentoRegister = convertToModel(MedicamentoDTO);
        data.save(medicamentoRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "El medicamento ha sido registrado"
        );
        return respuesta;
    }

    public responseDTO updateMedicamento(int id, MedicamentoDTO DTO) {
        Optional<Medicamento> medicamentoOpt = findById(id);
        if (!medicamentoOpt.isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(),
                "El medicamento no existe"
            );
            return respuesta;
        }
        Medicamento existingMedicamento = medicamentoOpt.get();
        existingMedicamento.setNombre(DTO.getNombre());
        data.save(existingMedicamento);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "El medicamento ha sido actualizado"
        );
        return respuesta;
    }

    public MedicamentoDTO convertToDTO(Medicamento medicamento) {
        MedicamentoDTO medicamentoDTO = new MedicamentoDTO(
            medicamento.getNombre()
        );
        return medicamentoDTO;
    }

    public Medicamento convertToModel(MedicamentoDTO medicamentoDTO) {
        Medicamento medicamento = new Medicamento(
            0,
            medicamentoDTO.getNombre()
        );
        return medicamento;
    }
}
