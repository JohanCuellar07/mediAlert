package com.sena.mediAlert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.mediAlert.model.Bitacora;

public interface IBitacora extends JpaRepository<Bitacora, Integer> {
    @Query("SELECT b FROM Bitacora b")
    List<Bitacora> findAll();

    @Query("SELECT b FROM Bitacora b WHERE b.paciente.id = :pacienteId")
    List<Bitacora> findAllByPacienteId(@Param("pacienteId") int pacienteId);

    @Query("SELECT b FROM Bitacora b WHERE b.medicamento.id = :medicamentoId")
    List<Bitacora> findAllByMedicamentoId(@Param("medicamentoId") int medicamentoId);
}
