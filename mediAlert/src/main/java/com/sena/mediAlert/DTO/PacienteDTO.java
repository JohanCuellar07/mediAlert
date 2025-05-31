package com.sena.mediAlert.DTO;

import java.time.LocalDate;

public class PacienteDTO {
    private String nombre;

    private LocalDate fecha_nacimiento;

    private String correo;

    public PacienteDTO() {
    }

    public PacienteDTO(String nombre, LocalDate fecha_nacimiento, String correo) {
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
