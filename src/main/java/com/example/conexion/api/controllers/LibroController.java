package com.example.conexion.api.controllers;


import com.example.conexion.api.models.LibroResumenDto;
import com.example.conexion.api.services.interfaces.LogicaMjsInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {
    // Aquí puedes definir los métodos para manejar las solicitudes HTTP
    // Por ejemplo, un metodo Get que vuelve un mjs

    @Autowired
    private LogicaMjsInterf libroService;

    @GetMapping("/buscar/{titulo}")
    public LibroResumenDto buscarLibro(@PathVariable("titulo") String titulo) {
        return libroService.buscarLibroPorTitulo(titulo);
    }

    @PostMapping("/guardar")
    public String guardarLibro(@RequestBody() LibroResumenDto payload) {
        return libroService.guardarLibro(payload);
    }

    @GetMapping("/listar")
    public List<LibroResumenDto> listarLibros() {
        return libroService.listarLibrosEnDB();
    }

    @GetMapping("/autores")
    public List<String> listarAutores() {
        return libroService.ListarAutoresEnDB();
    }

    @GetMapping("/listar-por-idioma")
    public ResponseEntity<List<LibroResumenDto>> listarLibrosPorIdioma(@RequestParam String idioma) {
        List<LibroResumenDto> libros = libroService.listarLibrosPorIdioma(idioma);
        if (libros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/autores-vivos")
    public ResponseEntity<List<String>> listarAutoresVivosEnAnio(@RequestParam int anio) {
        List<String> autores = libroService.listarAutoresVivosEnAnio(anio);
        if (autores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(autores);
    }
}
