package com.sena.mediAlert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.mediAlert.model.Medicamento;

public interface IMedicamento extends JpaRepository<Medicamento, Integer> {
    @Query("SELECT m FROM medicamento m")
    List<Medicamento> findAll();
}
