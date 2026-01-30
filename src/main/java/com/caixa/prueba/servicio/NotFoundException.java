package com.caixa.prueba.servicio;

/**
 * Excepción para recursos no encontrados (404)
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
