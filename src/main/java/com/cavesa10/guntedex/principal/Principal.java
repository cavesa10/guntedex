package com.cavesa10.guntedex.principal;

import com.cavesa10.guntedex.model.*;
import com.cavesa10.guntedex.repository.LibroRepository;
import com.cavesa10.guntedex.service.ConsumoAPI;
import com.cavesa10.guntedex.service.ConvierteDatos;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private final Scanner sc = new Scanner(System.in);
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();
    private final LibroRepository repositorio;

    public Principal(LibroRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void mostrarMenu() {
        var option = -1;
        while (option != 0) {
            var menu = """
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    buscarLibroTitulo();
                    break;
                case 2:
                    System.out.println("No hay nada :D");
                    break;
                case 3:
                    System.out.println("No hay nada3 :D");
                    break;
                case 4:
                    System.out.println("No hay nada4 :D");
                    break;
                case 5:
                    System.out.println("No hay nada5 :D");
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación Guntedex");
                    break;
                default:
                    System.out.println("******* Opción inválida *******");
            }
        }
    }

    private void buscarLibroTitulo() {
        System.out.println("Ingrese el titulo del libro: ");
        var tituloLibro = sc.nextLine();
        String URL_BASE = "https://gutendex.com/books/?search=";
        var json = consumoApi.obtenerDatos(URL_BASE + tituloLibro.replace(" ", "%20"));
        System.out.println(json);
        Datos datosLibro = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibro> libroBuscado = datosLibro.resultados().stream().findFirst();

        if (libroBuscado.isPresent()) {
            List<Libro> datosLibroBuscado = libroBuscado.stream().map(Libro::new).toList();
            Author autorBuscado = libroBuscado.stream().flatMap(b -> b.autores().stream().map(Author::new))
                    .toList().stream().findFirst().get();
            System.out.println(autorBuscado.toString());
            System.out.println(datosLibroBuscado.toString());
            autorBuscado.setLibros(datosLibroBuscado);
            try {
                repositorio.save(autorBuscado);
                System.out.println("Libro guardado exitosamente.");
            } catch (DataIntegrityViolationException e) {
                System.out.println("Error: Ya existe un libro con el título '" + tituloLibro + "' en la base de datos.");
            }
//            repositorio.save(autorBuscado);
        } else {
            System.out.println("Libro no encontrado: " + libroBuscado);
        }
    }

}
