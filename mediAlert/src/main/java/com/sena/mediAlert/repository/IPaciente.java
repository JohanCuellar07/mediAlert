package com.sena.mediAlert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.mediAlert.model.Paciente;

public interface IPaciente extends JpaRepository<Paciente, Integer> {
    @Query("SELECT p FROM paciente p")
    List<Paciente> findAll();
}
