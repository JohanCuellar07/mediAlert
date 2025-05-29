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
    private Paciente pacienteid;

    @ManyToOne
    @JoinColumn(name = "medicamentoid", nullable = false)
    private Medicamento medicamentoid;

    public Paciente_Medicamento() {
    }

    public Paciente_Medicamento(int id, Paciente pacienteid, Medicamento medicamentoid) {
        this.pacienteid = pacienteid;
        this.medicamentoid = medicamentoid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPacienteid() {
        return pacienteid;
    }

    public void setPacienteid(Paciente pacienteid) {
        this.pacienteid = pacienteid;
    }

    public Medicamento getMedicamentoid() {
        return medicamentoid;
    }

    public void setMedicamentoid(Medicamento medicamentoid) {
        this.medicamentoid = medicamentoid;
    }
}
