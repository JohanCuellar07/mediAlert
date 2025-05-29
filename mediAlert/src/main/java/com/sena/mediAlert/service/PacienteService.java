package com.sena.mediAlert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.mediAlert.model.Paciente;
import com.sena.mediAlert.repository.IPaciente;

@Service
public class PacienteService {
    @Autowired
    private IPaciente data;

    public List<Paciente> findAll() {
        return data.findAll();
    }

}
