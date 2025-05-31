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

import com.sena.mediAlert.DTO.Paciente_MedicamentoDTO;
import com.sena.mediAlert.DTO.responseDTO;
import com.sena.mediAlert.service.Paciente_MedicamentoService;

@RestController
@RequestMapping("/paciente_medicamento")
public class Paciente_MedicamentoController {
    @Autowired
    private Paciente_MedicamentoService pacienteMedicamentoService;

    @PostMapping("/")
    public ResponseEntity<Object> registerPacienteMedicamento(@RequestBody Paciente_MedicamentoDTO pacienteMedicamento){
        responseDTO respuesta = pacienteMedicamentoService.save(pacienteMedicamento);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePacienteMedicamento(@PathVariable int id, @RequestBody Paciente_MedicamentoDTO DTO){
        responseDTO respuesta = pacienteMedicamentoService.updatePacienteMedicamento(id, DTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllPacienteMedicamento(){
        var listaPacienteMedicamento = pacienteMedicamentoService.findAll();
        return new ResponseEntity<>(listaPacienteMedicamento, HttpStatus.OK);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<Object> getByPacienteId(@PathVariable int pacienteId){
        var listaPorPaciente = pacienteMedicamentoService.findAllByPacienteId(pacienteId);
        return new ResponseEntity<>(listaPorPaciente, HttpStatus.OK);
    }

    @GetMapping("/medicamento/{medicamentoId}")
    public ResponseEntity<Object> getByMedicamentoId(@PathVariable int medicamentoId){
        var listaPorMedicamento = pacienteMedicamentoService.findAllByMedicamentoId(medicamentoId);
        return new ResponseEntity<>(listaPorMedicamento, HttpStatus.OK);
    }

    @DeleteMapping("/paciente/{pacienteId}")
    public ResponseEntity<Object> deleteByPacienteId(@PathVariable int pacienteId){
        responseDTO respuesta = pacienteMedicamentoService.deleteByPacienteId(pacienteId);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @DeleteMapping("/medicamento/{medicamentoId}")
    public ResponseEntity<Object> deleteByMedicamentoId(@PathVariable int medicamentoId){
        responseDTO respuesta = pacienteMedicamentoService.deleteByMedicamentoId(medicamentoId);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
