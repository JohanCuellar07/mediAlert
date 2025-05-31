package com.sena.mediAlert.DTO;

import java.time.LocalTime;

import com.sena.mediAlert.model.Medicamento;
import com.sena.mediAlert.model.Paciente;

public class Paciente_MedicamentoDTO {
    private Paciente paciente;

    private Medicamento medicamento;

    private String dosis;

    private LocalTime horario;

    public Paciente_MedicamentoDTO(Paciente paciente, Medicamento medicamento, String dosis, LocalTime horario) {
        this.paciente = paciente;
        this.medicamento = medicamento;
        this.dosis = dosis;
        this.horario = horario;
    }

    public Paciente getPacienteid() {
        return paciente;
    }

    public void setPacienteid(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medicamento getMedicamentoid() {
        return medicamento;
    }

    public void setMedicamentoid(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }
}
