package com.example.mapaestaciones;

public class Vehiculo {

    private String matricula;
    private String categoria;
    private String marca;
    private String modelo;
    private String descripcion;
    private Double precio;
    private String nombreOficina;

    public Vehiculo(String matricula, String categoria, String marca, String modelo,
                    String descripcion, Double precio, String nombreOficina){
        this.matricula = matricula;
        this.categoria = categoria;
        this.marca = marca;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.nombreOficina = nombreOficina;
    }

    public String getMarca(){ return marca; }

    public String getModelo(){ return modelo; }

    public Double getPrecio(){ return precio; }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreOficina() {
        return nombreOficina;
    }

    public void setNombreOficina(String nombreOficina) {
        this.nombreOficina = nombreOficina;
    }

    public String toString(){ return marca+" - "+modelo; }

    public String toString2() {return marca;
    }
}
