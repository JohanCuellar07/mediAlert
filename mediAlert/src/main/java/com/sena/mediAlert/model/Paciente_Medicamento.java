package com.sena.mediAlert.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "paciente_medicamento")
public class Paciente_Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "pacienteid", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medicamentoid", nullable = false)
    private Medicamento medicamento;

    public Paciente_Medicamento() {
    }

    public Paciente_Medicamento(int id, Paciente paciente, Medicamento medicamento) {
        this.id = id;
        this.paciente = paciente;
        this.medicamento = medicamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
