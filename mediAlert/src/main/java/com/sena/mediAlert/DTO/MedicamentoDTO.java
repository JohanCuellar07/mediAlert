package com.sena.mediAlert.DTO;

public class MedicamentoDTO {
    private String nombre;

    public MedicamentoDTO(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
