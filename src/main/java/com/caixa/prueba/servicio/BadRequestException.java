package com.caixa.prueba.servicio;

/**
 * Excepción para peticiones incorrectas (400)
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
