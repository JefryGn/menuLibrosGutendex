package com.example.conexion.api.models;


import java.util.ArrayList;

public class LibroDto {

    private ArrayList<ResultsDto> datosLibros;


    public ArrayList<ResultsDto> getDatosLibros() {
        return datosLibros;
    }

    public void setDatosLibros(ArrayList<ResultsDto> datosLibros) {
        this.datosLibros = datosLibros;
    }


    public LibroDto() {
    }

    @Override
    public String toString() {
        return "LibroDto{" +
                "datosLibros=" + datosLibros +
                '}';
    }
}
