package com.example.conexion.api.models;

public class DatoDto {

    private  String nombre;




    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DatoDto(String nombre) {
        this.nombre = nombre;
    }



    @Override
    public String toString() {
        return "DatoDto{" + "nombre='" + nombre + '\'' + '}';
    }
}
