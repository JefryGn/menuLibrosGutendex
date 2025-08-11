package com.example.conexion.api.services;


import com.example.conexion.api.entities.Libro;
import com.example.conexion.api.models.LibroResumenDto;
import com.example.conexion.api.repositories.LibroRepository;
import com.example.conexion.api.services.interfaces.LogicaMjsInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class LogicaMjsImpl implements LogicaMjsInterf {

    @Autowired
    private LibroRepository libroRepository;

    private final String GUTENDEX_URL = "https://gutendex.com/books/";
    private final RestTemplate restTemplate = new RestTemplate();
    private static String COMPLEMENT_SEARCH_URL = "?search=";

    @Override
    public LibroResumenDto buscarLibroPorTitulo(String titulo) {
        // 1. Consumir API
        String url = GUTENDEX_URL + COMPLEMENT_SEARCH_URL + titulo;
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        List<Map<String, Object>> resultados = (List<Map<String, Object>>) response.get("results");

        Optional<Map<String, Object>> libroEncontrado = resultados.stream()
                .filter(libro -> titulo.equalsIgnoreCase((String) libro.get("title")))
                .findFirst();

        if (libroEncontrado.isEmpty()) return null;

        Map<String, Object> libroData = libroEncontrado.get();

        // 2. Mapear respuesta a DTO
        LibroResumenDto libro = new LibroResumenDto();
        libro.setTitulo((String) libroData.get("title"));

        // Autores (la API devuelve un array de autores con "name")
        List<String> autores = ((List<Map<String, Object>>) libroData.get("authors"))
                .stream()
                .map(a -> (String) a.get("name"))
                .collect(Collectors.toList());
        libro.setAutores(autores);

        // Idiomas
        libro.setIdiomas((List<String>) libroData.get("languages"));

        // Descargas
        libro.setNumeroDescargas((Integer) libroData.get("download_count"));

        return libro;
    }

    @Override
    public String guardarLibro(LibroResumenDto payload) {
        Libro libro = new Libro();
        libro.setTitulo(payload.getTitulo());
        libro.setAutores(payload.getAutores());
        libro.setIdiomas(payload.getIdiomas());
        libro.setNumeroDescargas(payload.getNumeroDescargas());

        Libro result = libroRepository.save(libro);

        return "Ok";
    }

    @Override
    public List<LibroResumenDto> listarLibrosEnDB() {
        return libroRepository.findAll()
                .stream()
                .map(libro -> new LibroResumenDto(
                        libro.getTitulo(),
                        libro.getAutores(),
                        libro.getIdiomas(),
                        libro.getNumeroDescargas()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> ListarAutoresEnDB() {
        return libroRepository.findAll()
                .stream()
                .flatMap(libro -> libro.getAutores().stream()) // aplanar List<String>
                .map(String::trim) // opcional: quitar espacios en blanco
                .distinct() // quitar repetidos
                .sorted() // ordenar alfabéticamente
                .collect(Collectors.toList());
    }

    @Override
    public List<LibroResumenDto> listarLibrosPorIdioma(String idioma) {
        return libroRepository.findAll()
                .stream()
                .filter(libro -> libro.getIdiomas().contains(idioma))
                .map(libro -> new LibroResumenDto(
                        libro.getTitulo(),
                        libro.getAutores(),
                        libro.getIdiomas(),
                        libro.getNumeroDescargas()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> listarAutoresVivosEnAnio(int anio) {
        List<String> autoresTotales = new ArrayList<>();

        // Recorrer las primeras 3 páginas
        for (int page = 1; page <= 3; page++) {
            String url = GUTENDEX_URL + "?author_year_start=" + anio + "&page=" + page;

            try {
                ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

                if (response.getBody() == null || !response.getBody().containsKey("results")) {
                    continue;
                }

                List<Map<String, Object>> resultados = (List<Map<String, Object>>) response.getBody().get("results");

                for (Map<String, Object> libro : resultados) {
                    List<Map<String, Object>> autores = (List<Map<String, Object>>) libro.get("authors");
                    for (Map<String, Object> autor : autores) {
                        String nombre = (String) autor.get("name");
                        autoresTotales.add(nombre);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error consultando página " + page + ": " + e.getMessage());
            }
        }

        // Eliminar duplicados y ordenar
        return autoresTotales.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }


}
