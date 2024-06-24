package com.cavesa10.guntedex.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros", uniqueConstraints = @UniqueConstraint(columnNames = {"titulo"}))
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Lenguaje idiomas;
    private Integer descarga;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Author autor;

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.idiomas = Lenguaje.fromString(datosLibro.idiomas().stream().limit(1).collect(Collectors.joining()));
        this.descarga = datosLibro.descarga();
    }

    public Libro() {

    }

    public Author getAutor() {
        return autor;
    }

    public void setAutor(Author autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", idiomas=" + idiomas +
                ", descarga=" + descarga +
                ", autor=" + autor +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }




    public Integer getDescarga() {
        return descarga;
    }

    public void setDescarga(Integer descarga) {
        this.descarga = descarga;
    }

    public Lenguaje getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Lenguaje idiomas) {
        this.idiomas = idiomas;
    }
}
