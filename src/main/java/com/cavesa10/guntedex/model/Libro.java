package com.cavesa10.guntedex.model;

import java.util.List;

public class Libro {

    private Long id;
    private String titulo;
    private List<Author> autores;
    private Lenguaje idiomas;
    private Integer descarga;

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

    public List<Author> getAutores() {
        return autores;
    }

    public void setAutores(List<Author> autores) {
        this.autores = autores;
    }

    public Lenguaje getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Lenguaje idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getDescarga() {
        return descarga;
    }

    public void setDescarga(Integer descarga) {
        this.descarga = descarga;
    }
}
