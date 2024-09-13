package org.example.cli;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;

@SpringBootApplication
@Order(2)
public class CliApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CliApplication.class, args);
		System.out.println("End of main");
	}

	public void run(String... args) throws Exception {
		System.out.println("Hello from Spring Boot Application!");
	}
}
