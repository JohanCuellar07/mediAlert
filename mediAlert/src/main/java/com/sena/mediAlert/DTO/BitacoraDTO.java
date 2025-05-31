package com.sena.mediAlert.DTO;

import java.time.LocalDateTime;

import com.sena.mediAlert.model.Medicamento;
import com.sena.mediAlert.model.Paciente;

public class BitacoraDTO {
    private Paciente paciente;

    private Medicamento medicamento;

    private LocalDateTime fechaenvio;

    public BitacoraDTO(Paciente paciente, Medicamento medicamento, LocalDateTime fechaenvio) {
        this.paciente = paciente;
        this.medicamento = medicamento;
        this.fechaenvio = fechaenvio;
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

    public LocalDateTime getFechaenvio() {
        return fechaenvio;
    }

    public void setFechaenvio(LocalDateTime fechaenvio) {
        this.fechaenvio = fechaenvio;
    }
}
