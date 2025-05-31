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

import com.sena.mediAlert.DTO.MedicamentoDTO;
import com.sena.mediAlert.DTO.responseDTO;
import com.sena.mediAlert.service.MedicamentoService;

@RestController
@RequestMapping("/medicamento")
public class MedicamentoController {
    @Autowired
    private MedicamentoService medicamentoService;

    @PostMapping("/")
    public ResponseEntity<Object> registerMedicamento(@RequestBody MedicamentoDTO medicamento){
        responseDTO respuesta = medicamentoService.save(medicamento);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMedicamento(@PathVariable int id, @RequestBody MedicamentoDTO DTO){
        responseDTO respuesta = medicamentoService.updateMedicamento(id, DTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllMedicamento(){
        var listaMedicamentos = medicamentoService.findAll();
        return new ResponseEntity<>(listaMedicamentos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneMedicamento(@PathVariable int id){
        var medicamento = medicamentoService.findById(id);
        return new ResponseEntity<>(medicamento, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMedicamento(@PathVariable int id){
        responseDTO respuesta = medicamentoService.deleteMedicamento(id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
