package com.sena.mediAlert.DTO;

import java.sql.Time;

import com.sena.mediAlert.model.Medicamento;
import com.sena.mediAlert.model.Paciente;

public class Paciente_MedicamentoDTO {
    private Paciente paciente;

    private Medicamento medicamento;

    private String dosis;

    private Time horario;

    public Paciente_MedicamentoDTO(Paciente paciente, Medicamento medicamento, String dosis, Time horario) {
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

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }
}
