package com.cavesa10.guntedex.principal;

import com.cavesa10.guntedex.model.*;
import com.cavesa10.guntedex.repository.LibroRepository;
import com.cavesa10.guntedex.service.ConsumoAPI;
import com.cavesa10.guntedex.service.ConvierteDatos;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;
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
                                        
                    Seleccione una opción que desea:
                                        
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
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
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
        } else {
            System.out.println("Libro no encontrado: " + libroBuscado);
        }
    }

    private void listarLibros() {
        List<Libro> listLibros = repositorio.listarLibros();
        listLibros.forEach(l -> System.out.println("------ Libros ------" +
                "\nTítulo: " + l.getTitulo() +
                "\nAutor: " + l.getAutor().getNombre() +
                "\nIdioma: " + l.getIdiomas().getIdioma() + " - " + l.getIdiomas().getLenguajeAPI() +
                "\nDescargas: " + l.getDescarga() +
                "\n-------------------"));
        System.out.println("Presione la tecla Enter para continuar");
        sc.nextLine();
    }

    private void listarAutores() {
        List<Author> listaAutores = repositorio.findAll();
        System.out.println(listaAutores.toString());
        System.out.println("Presione la tecla Enter para continuar");
        sc.nextLine();
    }

    private void listarAutoresVivos() {
        System.out.println("Ingrese el año vivo del autor(es) que desea buscar");
        var yearBuscado = sc.nextInt();
        Optional<List<Author>> listaAutoresVivos = Optional.ofNullable(repositorio.listarAutoresVivos(yearBuscado));
        List<Author> autoresVivos = listaAutoresVivos.orElse(Collections.emptyList());
        if (autoresVivos.isEmpty()) {
//        System.out.println(listaAutoresVivos.toString());
            System.out.println("No se encontraron autores vivos en el año " + yearBuscado);
            sc.nextLine();
        } else {
            autoresVivos.forEach(a -> System.out.println(a.toString()));
            sc.nextLine();
        }
    }

    private void listarLibrosPorIdioma() {
        var submenu = true;
        while (submenu) {
            System.out.println("""
                    Seleccione el idioma de los libros a buscar:
                       
                        en - English
                        es - Spanish
                        fr - French
                        it - Italian
                        pt - Portuguese
                                  
                    """);
            Optional<Lenguaje> idomasc = Lenguaje.comprobar(sc.nextLine());
            if (idomasc.isPresent()) {
                Lenguaje idioma = idomasc.get();
                System.out.println(idioma);
                List<Libro> listLibros = repositorio.listarLibrosPorIdiomas(idioma);
                if (listLibros.isEmpty()) {
                    System.out.println("No hay libros con el idioma: " + idomasc.get());
                }else {
                    listLibros.forEach(l -> System.out.println(l.toString()));
                }
                submenu = false;
            }else {
                System.out.println("Opción Invalida");
            }
        }
    }

}
