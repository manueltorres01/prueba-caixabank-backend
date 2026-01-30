package com.caixa.prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación.
 * 
 * Es el punto de entrada de Spring Boot.
 * Desde aquí se inicia todo el contexto,
 * se cargan los beans y se levanta el servidor.
 */
@SpringBootApplication
public class PruebaApplication {

    /**
     * Método main.
     * Arranca la aplicación.
     */
    public static void main(String[] args) {
        SpringApplication.run(PruebaApplication.class, args);
    }
}
