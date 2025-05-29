package com.sena.mediAlert.DTO;

import java.sql.Date;

import com.sena.mediAlert.model.Medicamento;
import com.sena.mediAlert.model.Paciente;

public class BitacoraDTO {
    private Paciente paciente;

    private Medicamento medicamento;

    private Date fechaenvio;

    public BitacoraDTO(Paciente paciente, Medicamento medicamento, Date fechaenvio) {
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

    public Date getFechaenvio() {
        return fechaenvio;
    }

    public void setFechaenvio(Date fechaenvio) {
        this.fechaenvio = fechaenvio;
    }
}
