package com.cavesa10.guntedex.model;

public enum Lenguaje {
    ESPANOL("es"),
    INGLES("en"),
    FRANCES("fr"),
    PORTUGUES("pt"),
    ALEMAN("de");

    private final String lenguajeAPI;

    Lenguaje(String lenguajeAPI) {
        this.lenguajeAPI = lenguajeAPI;
    }

    public String getLenguajeAPI() {
        return lenguajeAPI;
    }
}
