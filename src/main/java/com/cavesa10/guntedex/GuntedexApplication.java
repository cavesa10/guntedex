package com.cavesa10.guntedex;

import com.cavesa10.guntedex.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GuntedexApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GuntedexApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.mostrarMenu();
	}
}
