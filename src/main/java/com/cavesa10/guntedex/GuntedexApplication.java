package com.cavesa10.guntedex;

import com.cavesa10.guntedex.principal.Principal;
import com.cavesa10.guntedex.repository.LibroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GuntedexApplication implements CommandLineRunner {
	private final LibroRepository repository;

    public GuntedexApplication(LibroRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
		SpringApplication.run(GuntedexApplication.class, args);
	}

	@Override
	public void run(String... args)  {
		Principal principal = new Principal(repository);
		principal.mostrarMenu();
	}
}
