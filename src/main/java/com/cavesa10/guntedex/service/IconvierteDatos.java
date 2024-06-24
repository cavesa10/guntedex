package com.cavesa10.guntedex.service;

public interface IconvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
