package com.example.conexion.api.models;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

public class LibroResumenDto {

    private String titulo;
    private List<String> autores;
    private List<String> idiomas;
    private Integer numeroDescargas;

        public LibroResumenDto(String titulo, List<String> autores, List<String> idiomas, Integer numeroDescargas) {
            this.titulo = titulo;
            this.autores = autores;
            this.idiomas = idiomas;
            this.numeroDescargas = numeroDescargas;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public LibroResumenDto() {
    }

    @Override
    public String toString() {
        return "LibroResumenDto{" +
                "titulo='" + titulo + '\'' +
                ", autores=" + autores +
                ", idiomas=" + idiomas +
                ", numeroDescargas=" + numeroDescargas +
                '}';
    }
}
