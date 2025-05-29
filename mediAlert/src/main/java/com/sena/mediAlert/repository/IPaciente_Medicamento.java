package com.sena.mediAlert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.mediAlert.model.Paciente_Medicamento;

public interface IPaciente_Medicamento extends JpaRepository<Paciente_Medicamento, Integer> {
    @Query("SELECT pm FROM paciente_medicamento pm WHERE pm.paciente.id = :pacienteId")
    List<Paciente_Medicamento> findALLByPacienteId(@Param("pacienteId") int pacienteId);

    @Query("SELECT pm FROM paciente_medicamento pm WHERE pm.medicamento.id = :medicamentoId")
    List<Paciente_Medicamento> findALLByMedicamentoId(@Param("medicamentoId") int medicamentoId);
}
