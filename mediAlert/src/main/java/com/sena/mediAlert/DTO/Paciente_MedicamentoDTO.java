package com.sena.mediAlert.DTO;

import com.sena.mediAlert.model.Medicamento;
import com.sena.mediAlert.model.Paciente;

public class Paciente_MedicamentoDTO {
    private Paciente paciente;

    private Medicamento medicamento;

    public Paciente_MedicamentoDTO(Paciente paciente, Medicamento medicamento) {
        this.paciente = paciente;
        this.medicamento = medicamento;
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

}
