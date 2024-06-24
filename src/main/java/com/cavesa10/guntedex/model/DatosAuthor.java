package com.cavesa10.guntedex.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAuthor(@JsonAlias("name") String nombre,
                          @JsonAlias("birth_year") Integer fechaDeNacimiento,
                          @JsonAlias("death_year") Integer fechaMuerte) {
}
