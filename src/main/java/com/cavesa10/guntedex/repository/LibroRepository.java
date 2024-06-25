package com.cavesa10.guntedex.repository;

import com.cavesa10.guntedex.model.Author;
import com.cavesa10.guntedex.model.Lenguaje;
import com.cavesa10.guntedex.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Author,Long> {

    @Query("select l from Author a join a.libros l")
    List<Libro> listarLibros();

    @Override
    List<Author> findAll();

    @Query("select a from Author a where a.fechaMuerte >= :yearBuscado and a.fechaDeNacimiento <= :yearBuscado ")
    List<Author> listarAutoresVivos(int yearBuscado);

    @Query("select l from Author a join a.libros l where l.idiomas = :idioma")
    List<Libro> listarLibrosPorIdiomas(Lenguaje idioma);
}
