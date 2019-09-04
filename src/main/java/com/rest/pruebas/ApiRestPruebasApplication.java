package com.rest.pruebas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.rest" })
public class ApiRestPruebasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestPruebasApplication.class, args);
	}

}
