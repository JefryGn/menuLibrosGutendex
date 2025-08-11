package com.example.conexion.api;

import com.example.conexion.api.controllers.LibroController;
import com.example.conexion.api.models.LibroResumenDto;
import com.example.conexion.api.services.LogicaMjsImpl;
import com.example.conexion.api.services.interfaces.LogicaMjsInterf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@SpringBootApplication
public class ConexionapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConexionapiApplication.class, args);

        Scanner scanner = new Scanner(System.in);
        RestTemplate restTemplate = new RestTemplate();

        while (true) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Buscar y registrar libro (debe ser nombre exacto del libro)");
            System.out.println("2. Listar libros registrados");
            System.out.println("3. Listar autores registrados");
            System.out.println("4. Listar libros registrados por idioma (es/en)");
            System.out.println("5. Listar autores vivos en un año (primeras 3 pag. de gutendex)");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> {
                    System.out.print("Escribe el nombre del libro o el autor: ");
                    String terminoBusqueda = scanner.nextLine();

                    // GET
                    String getUrl = "http://localhost:8080/libros/buscar/" + terminoBusqueda;
                    try {
                        Map<String, Object> response = restTemplate.getForObject(getUrl, Map.class);

                        if (response != null && !response.isEmpty()) {
                            System.out.println("\n📘 Libro encontrado:");
                            imprimirLibroBonito(response);

                            // Preparar datos para POST
                            Map<String, Object> libroPayload = new HashMap<>();
                            libroPayload.put("titulo", response.get("titulo"));

                            Object autoresObj = response.get("autores");
                            if (autoresObj instanceof List<?> autoresList) {
                                libroPayload.put("autores", autoresList.toArray(new String[0]));
                            }

                            Object idiomasObj = response.get("idiomas");
                            if (idiomasObj instanceof List<?> idiomasList) {
                                libroPayload.put("idiomas", idiomasList.toArray(new String[0]));
                            }

                            libroPayload.put("numeroDescargas", response.get("numeroDescargas"));

                            // POST
                            String postUrl = "http://localhost:8080/libros/guardar";
                            HttpHeaders headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);

                            HttpEntity<Map<String, Object>> request = new HttpEntity<>(libroPayload, headers);
                            ResponseEntity<String> postResponse = restTemplate.postForEntity(postUrl, request, String.class);

                            System.out.println("\n✅ Libro guardado con éxito.");
                        } else {
                            System.out.println("❌ No se encontró información del libro.");
                        }
                    } catch (Exception e) {
                        System.out.println("⚠️ Error al buscar o guardar el libro: " + e.getMessage());
                    }
                }

                case "2" -> { // Listar libros
                    String urlListar = "http://localhost:8080/libros/listar";
                    try {
                        List<Map<String, Object>> libros = restTemplate.getForObject(urlListar, List.class);

                        if (libros != null && !libros.isEmpty()) {
                            System.out.println("\n📚 Lista de libros guardados:");
                            for (Map<String, Object> libro : libros) {
                                imprimirLibroBonito(libro);
                            }
                        } else {
                            System.out.println("📭 No hay libros guardados.");
                        }
                    } catch (Exception e) {
                        System.out.println("⚠️ Error al listar libros: " + e.getMessage());
                    }
                }


                case "3" -> { // Listar autores
                    String urlAutores = "http://localhost:8080/libros/autores";
                    try {
                        List<String> autores = restTemplate.getForObject(urlAutores, List.class);

                        if (autores != null && !autores.isEmpty()) {
                            System.out.println("\n✍️ Autores registrados:");
                            autores.forEach(a -> System.out.println(" - " + a));
                        } else {
                            System.out.println("📭 No hay autores registrados.");
                        }
                    } catch (Exception e) {
                        System.out.println("⚠️ Error al listar autores: " + e.getMessage());
                    }
                }




                case "4" -> {
                    System.out.print("Ingrese el idioma (es o en): ");
                    String idioma = scanner.nextLine();
                    String baseUrl = "http://localhost:8080/libros";

                    try {
                        LibroResumenDto[] libros = restTemplate.getForObject(
                                baseUrl + "/listar-por-idioma?idioma=" + idioma,
                                LibroResumenDto[].class
                        );

                        if (libros != null && libros.length > 0) {
                            System.out.println("\n📚 Libros en idioma '" + idioma + "':");
                            for (LibroResumenDto libro : libros) {
                                System.out.println("- Título: " + libro.getTitulo());
                                System.out.println("  Autor: " + libro.getAutores());
                                System.out.println("  Idioma: " + libro.getIdiomas());
                                System.out.println("  Descargas: " + libro.getNumeroDescargas());
                                System.out.println("-----------------------------------");
                            }
                        } else {
                            System.out.println("No se encontraron libros en el idioma '" + idioma + "'.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error al consultar los libros: " + e.getMessage());
                    }
                }


                case "5" -> {
                    System.out.print("Ingrese el año: ");
                    int anio = scanner.nextInt();
                    scanner.nextLine();
                    String baseUrl = "http://localhost:8080/libros";

                    try {
                        String[] autores = restTemplate.getForObject(
                                baseUrl + "/autores-vivos?anio=" + anio,
                                String[].class
                        );

                        if (autores != null && autores.length > 0) {
                            System.out.println("\n👨‍💼 Autores vivos en el año " + anio + ":");
                            Arrays.stream(autores).forEach(a -> System.out.println("- " + a));
                        } else {
                            System.out.println("No se encontraron autores vivos en ese año.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error al consultar autores: " + e.getMessage());
                    }
                }

                case "6" -> {
                    System.out.println("👋 Hasta luego.");
                    scanner.close();
                    System.exit(0);
                }

                default -> System.out.println("❌ Opción no válida. Intente nuevamente.");
            }
        }
    }

    public static void imprimirLibroBonito(Map<String, Object> libro) {
        System.out.println("─────────────────────────────────────");
        System.out.println("📖 Título: " + libro.get("titulo"));

        Object autoresObj = libro.get("autores");
        if (autoresObj instanceof List<?> autores) {
            System.out.println("✍️  Autor(es): " + String.join(", ", autores.stream().map(Object::toString).toList()));
        }

        Object idiomasObj = libro.get("idiomas");
        if (idiomasObj instanceof List<?> idiomas) {
            System.out.println("🌍 Idioma(s): " + String.join(", ", idiomas.stream().map(Object::toString).toList()));
        }

        System.out.println("⬇️  Descargas: " + libro.get("numeroDescargas"));
        System.out.println("─────────────────────────────────────");
    }

}