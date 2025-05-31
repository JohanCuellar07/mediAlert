package com.sena.mediAlert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.mediAlert.DTO.BitacoraDTO;
import com.sena.mediAlert.DTO.responseDTO;
import com.sena.mediAlert.model.Bitacora;
import com.sena.mediAlert.repository.IBitacora;

@Service
public class BitacoraService {
    @Autowired
    private IBitacora data;

    public List<Bitacora> findAll() {
        return data.findAll();
    }

    public Optional<Bitacora> findById(int id){
        return data.findById(id);
    }

    public List<Bitacora> findAllByPacienteId(int pacienteid){
        return data.findAllByPacienteId(pacienteid);
    }

    public List<Bitacora> findAllByMedicamentoId(int medicamentoid){
        return data.findAllByMedicamentoId(medicamentoid);
    }

    public responseDTO deleteByPacienteId(int pacienteid) {
        List<Bitacora> bitacora = findAllByPacienteId(pacienteid);
        if (bitacora.isEmpty()) {
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
        List<Bitacora> bitacora = findAllByMedicamentoId(medicamentoid);
        if (bitacora.isEmpty()) {
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

    public responseDTO save(BitacoraDTO BitacoraDTO) {
        if (BitacoraDTO.getPacienteid() == null || BitacoraDTO.getMedicamentoid() == null) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El paciente o el medicamento no puede estar vacio"
            );
            return respuesta;
        }
        if (BitacoraDTO.getFechaenvio() == null) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "La fecha de envio del medicamento no puede estar vacia"
            );
            return respuesta;
        }
        Bitacora bitacoraRegister = convertToModel(BitacoraDTO);
        data.save(bitacoraRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "El medicamento ha sido registrado"
        );
        return respuesta;
    }

    public responseDTO updateBitacora(int id, BitacoraDTO DTO) {
        if (DTO.getPacienteid() == null || DTO.getMedicamentoid() == null) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El paciente o el medicamento no puede estar vacio"
            );
            return respuesta;
        }
        if (DTO.getFechaenvio() == null) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "La fecha de envio del medicamento no puede estar vacia"
            );
            return respuesta;
        }
        Bitacora existingBitacora = data.findById(id).get();
        existingBitacora.setPacienteid(DTO.getPacienteid());
        existingBitacora.setMedicamentoid(DTO.getMedicamentoid());
        existingBitacora.setFechaenvio(DTO.getFechaenvio());
        data.save(existingBitacora);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "El medicamento ha sido actualizado"
        );
        return respuesta;
    }

    public BitacoraDTO convertToDTO(Bitacora bitacora) {
        BitacoraDTO bitacoraDTO = new BitacoraDTO(
            bitacora.getPacienteid(),
            bitacora.getMedicamentoid(),
            bitacora.getFechaenvio()
        );
        return bitacoraDTO;
    }

    public Bitacora convertToModel(BitacoraDTO bitacoraDTO) {
        Bitacora bitacora = new Bitacora(
            0,
            bitacoraDTO.getPacienteid(),
            bitacoraDTO.getMedicamentoid(),
            bitacoraDTO.getFechaenvio()
        );
        return bitacora;
    }
}
