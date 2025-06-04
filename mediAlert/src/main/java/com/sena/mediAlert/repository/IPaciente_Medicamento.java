package com.sena.mediAlert.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.mediAlert.model.EstadoRecordatorio;
import com.sena.mediAlert.model.Paciente_Medicamento;

import jakarta.transaction.Transactional;

public interface IPaciente_Medicamento extends JpaRepository<Paciente_Medicamento, Integer> {
    @Query("SELECT pm FROM paciente_medicamento pm WHERE pm.id = :id")
    List<Paciente_Medicamento> getPacienteMedicamentoByid(int id);

    @Query("SELECT pm FROM paciente_medicamento pm WHERE pm.paciente.id = :pacienteId")
    List<Paciente_Medicamento> findAllByPacienteId(@Param("pacienteId") int pacienteId);

    @Query("SELECT pm FROM paciente_medicamento pm WHERE pm.medicamento.id = :medicamentoId")
    List<Paciente_Medicamento> findAllByMedicamentoId(@Param("medicamentoId") int medicamentoId);

    @Query("SELECT pm FROM paciente_medicamento pm WHERE pm.hora = :hora")
    List<Paciente_Medicamento> findByHora(@Param("hora") LocalTime hora);

    @Query("SELECT pm FROM paciente_medicamento pm WHERE pm.estado = :estado")
    List<Paciente_Medicamento> findByEstado(EstadoRecordatorio estado);

    @Query("SELECT p FROM paciente_medicamento p WHERE p.hora <= :hora AND p.estado = 'SINENVIAR'")
    List<Paciente_Medicamento> findByHoraSinEnviar(@Param("hora") LocalTime hora);

    @Transactional
    @Modifying
    @Query("DELETE FROM paciente_medicamento pm WHERE pm.paciente.id = :pacienteId")
    void deleteByPacienteId(@Param("pacienteId") int pacienteId);

    @Transactional
    @Modifying
    @Query("DELETE FROM paciente_medicamento pm WHERE pm.medicamento.id = :medicamentoId")
    void deleteByMedicamentoId(@Param("medicamentoId") int medicamentoId);
}
