package com.example.conexion.api.services.interfaces;

import com.example.conexion.api.models.LibroResumenDto;

import java.util.List;

public interface LogicaMjsInterf {
  //  public String showMessage();
  LibroResumenDto buscarLibroPorTitulo(String titulo);

  String guardarLibro(LibroResumenDto payload);

  List<LibroResumenDto> listarLibrosEnDB();

  List<String> ListarAutoresEnDB();

  List<LibroResumenDto> listarLibrosPorIdioma(String idioma);

  List<String> listarAutoresVivosEnAnio(int anio);
}
