package com.sena.mediAlert.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "bitacora")
public class Bitacora {
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

    @Column(name = "fechaenvio", nullable = false)
    private Date fechaenvio;

    public Bitacora() {
    }

    public Bitacora(int id, Paciente paciente, Medicamento medicamento, Date fechaenvio) {
        this.id = id;
        this.paciente = paciente;
        this.medicamento = medicamento;
        this.fechaenvio = fechaenvio;
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

    public Date getFechaenvio() {
        return fechaenvio;
    }

    public void setFechaenvio(Date fechaenvio) {
        this.fechaenvio = fechaenvio;
    }
}
