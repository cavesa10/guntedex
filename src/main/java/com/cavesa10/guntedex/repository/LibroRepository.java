package com.cavesa10.guntedex.repository;

import com.cavesa10.guntedex.model.Author;
import com.cavesa10.guntedex.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Author,Long> {
}
