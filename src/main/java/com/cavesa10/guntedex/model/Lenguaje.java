package com.cavesa10.guntedex.model;

public enum Lenguaje {
    ES("es", "Español"),
    EN("en", "Ingles"),
    FR("fr", "Frances"),
    PT("pt", "Portugués"),
    DE("de", "Aleman");

    private final String lenguajeAPI;
    private final String idioma;

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

    public static Lenguaje fromEspanol(String text) {
        for (Lenguaje cat : Lenguaje.values()) {
            if (cat.idioma.equalsIgnoreCase(text)) {
                return cat;
            }
        }
        throw new IllegalArgumentException("Ninguna lenguaje encontrado: " + text);
    }
}
