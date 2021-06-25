package com.example.mapaestaciones;

import java.sql.Date;

public class Reserva {

    private Integer codigo;
    private String fechaInicio;
    private String fechaFin;
    private String matricula;
    private String nombreOficina;
    private String dni;

    public Reserva(Integer codigo, String fechaInicio, String fechaFin, String matricula,
                   String nombreOficina, String dni){
        this.codigo = codigo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.matricula = matricula;
        this.nombreOficina = nombreOficina;
        this.dni = dni;

    }
    public String getMatricula() {
        return matricula;
    }

    public String toString(){
        return codigo +" - "+ dni +" - "+ matricula;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNombreOficina() {
        return nombreOficina;
    }

    public void setNombreOficina(String nombreOficina) {
        this.nombreOficina = nombreOficina;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String toString2() {return codigo +" - "+ nombreOficina +" - "+ fechaInicio+" - "+ fechaFin;
    }
}
