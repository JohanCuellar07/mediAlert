package com.sena.mediAlert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.mediAlert.DTO.responseDTO;
import com.sena.mediAlert.DTO.BitacoraDTO;
import com.sena.mediAlert.service.BitacoraService;

@RestController
@RequestMapping("/bitacora")
public class BitacoraController {
    @Autowired
    private BitacoraService bitacoraService;

    @PostMapping("/")
    public ResponseEntity<Object> registerBitacora(@RequestBody BitacoraDTO bitacora){
        responseDTO respuesta = bitacoraService.save(bitacora);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBitacora(@PathVariable int id, @RequestBody BitacoraDTO bitacora){
        responseDTO respuesta = bitacoraService.updateBitacora(id, bitacora);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllBitacora(){
        var listaBitacoras = bitacoraService.findAll();
        return new ResponseEntity<>(listaBitacoras, HttpStatus.OK);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<Object> getByPacienteId(@PathVariable int pacienteId){
        var listaPorPaciente = bitacoraService.findAllByPacienteId(pacienteId);
        return new ResponseEntity<>(listaPorPaciente, HttpStatus.OK);
    }

    @GetMapping("/medicamento/{medicamentoId}")
    public ResponseEntity<Object> getByMedicamentoId(@PathVariable int medicamentoId){
        var listaPorMedicamento = bitacoraService.findAllByMedicamentoId(medicamentoId);
        return new ResponseEntity<>(listaPorMedicamento, HttpStatus.OK);
    }

    @DeleteMapping("/paciente/{pacienteId}")
    public ResponseEntity<Object> deleteByPacienteId(@PathVariable int pacienteId){
        responseDTO respuesta = bitacoraService.deleteByPacienteId(pacienteId);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @DeleteMapping("/medicamento/{medicamentoId}")
    public ResponseEntity<Object> deleteByMedicamentoId(@PathVariable int medicamentoId){
        responseDTO respuesta = bitacoraService.deleteByMedicamentoId(medicamentoId);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
