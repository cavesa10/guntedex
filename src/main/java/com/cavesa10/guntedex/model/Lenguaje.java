package com.cavesa10.guntedex.model;

import java.util.Optional;

public enum Lenguaje {
    ES("es", "Español"),
    EN("en", "Ingles"),
    FR("fr", "Frances"),
    PT("pt", "Portugués"),
    DE("de", "Aleman"),
    FALSE("false", "False"),;

    private final String lenguajeAPI;
    private final String idioma;



    public String getIdioma() {
        return idioma;
    }

    public String getLenguajeAPI() {
        return lenguajeAPI;
    }

    Lenguaje(String lenguajeAPI, String idioma) {
        this.lenguajeAPI = lenguajeAPI;
        this.idioma = idioma;
    }

    public static Lenguaje fromString(String text) {
        for (Lenguaje cat : Lenguaje.values()) {
            if (cat.lenguajeAPI.equalsIgnoreCase(text)) {
                return cat;
            }
        }
        throw new IllegalArgumentException("Ningún lenguaje encontrado: " + text);
    }

    public static Optional<Lenguaje> comprobar(String text) {
        for (Lenguaje cat : Lenguaje.values()) {
            if (cat.lenguajeAPI.equalsIgnoreCase(text)) {
                return Optional.of(cat);
            }
        }
        return Optional.empty();
    }

    public static Lenguaje fromEspanol(String text) {
        for (Lenguaje cat : Lenguaje.values()) {
            if (cat.idioma.equalsIgnoreCase(text)) {
                return cat;
            }
        }
        throw new IllegalArgumentException("Ninguna lenguaje encontrado: " + text);
    }
}
