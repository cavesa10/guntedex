package com.cavesa10.guntedex.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores", uniqueConstraints = @UniqueConstraint(columnNames = {"nombre"}))
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaMuerte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Author() {}

    public Author(DatosAuthor datosAuthor) {
        this.nombre = datosAuthor.nombre();
        this.fechaDeNacimiento = datosAuthor.fechaDeNacimiento();
        this.fechaMuerte = datosAuthor.fechaMuerte();
    }

    //
//    public Author(Author autorBuscado) {
//        this.name = autorBuscado.name;
//        this.birthYear = autorBuscado.birthYear;
//        this.deathYear = autorBuscado.deathYear;
//    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String name) {
        this.nombre = name;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaNacimiento) {
        this.fechaDeNacimiento = fechaNacimiento;
    }

    public Integer getFechaMuerte() {
        return fechaMuerte;
    }

    public void setFechaMuerte(Integer deathYear) {
        this.fechaMuerte = deathYear;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        libros.forEach(b -> b.setAutor(this));
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Author{" +
                ", name='" + nombre + '\'' +
                ", birthYear=" + fechaDeNacimiento +
                ", deathYear=" + fechaMuerte +
                ", libros=" + libros +
                '}';
    }
}
