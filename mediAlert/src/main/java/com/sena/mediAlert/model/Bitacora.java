package com.sena.mediAlert.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "Bitacora")
public class Bitacora {
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

    @Column(name = "fechaenvio", nullable = false)
    private Date fechaenvio;

    public Bitacora() {
    }

    public Bitacora(int id, Paciente pacienteid, Medicamento medicamentoid, Date fechaenvio) {
        this.id = id;
        this.pacienteid = pacienteid;
        this.medicamentoid = medicamentoid;
        this.fechaenvio = fechaenvio;
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

    public Date getFechaenvio() {
        return fechaenvio;
    }

    public void setFechaenvio(Date fechaenvio) {
        this.fechaenvio = fechaenvio;
    }
}
