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

import com.sena.mediAlert.DTO.PacienteDTO;
import com.sena.mediAlert.DTO.responseDTO;
import com.sena.mediAlert.service.PacienteService;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/")
    public ResponseEntity<Object> registerPaciente(PacienteDTO paciente){
        responseDTO respuesta = pacienteService.save(paciente);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePaciente(@PathVariable int id, @RequestBody PacienteDTO DTO){
        responseDTO respuesta = pacienteService.updatePaciente(id, DTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllPaciente(){
        var listaPacientes = pacienteService.findAll();
        return new ResponseEntity<>(listaPacientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePaciente(@PathVariable int id){
        var paciente = pacienteService.findById(id);
        return new ResponseEntity<>(paciente, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePaciente(@PathVariable int id){
        responseDTO respuesta = pacienteService.deletePaciente(id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}