package com.caixa.prueba.servicio;

import com.caixa.prueba.modelo.modelo;
import com.caixa.prueba.repositorio.repositorio;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de servicio.
 * Aquí va toda la lógica de negocio de las solicitudes.
 * 
 * No accede directamente al controlador ni a la base de datos,
 * solo coordina la información.
 */
@Service
public class request {

    // Repositorio en memoria
    private final repositorio repo;

    /**
     * Inyección del repositorio por constructor
     */
    public request(repositorio repo) {
        this.repo = repo;
    }

    /**
     * Crea una nueva solicitud de préstamo
     */
    public modelo crear(modelo m) {

        /*
         * Cuando recibimos datos desde el POST,
         * normalmente vienen sin ID, fecha ni estado.
         * 
         * Por eso, si no tiene ID, creamos un nuevo objeto
         * usando el constructor por defecto.
         */
        if (m.getId() == null || m.getId().isBlank()) {

            modelo nuevo = new modelo();

            // Copiamos los datos recibidos
            nuevo.setNombreSolicitante(m.getNombreSolicitante());
            nuevo.setImporteSolicitado(m.getImporteSolicitado());
            nuevo.setDivisa(m.getDivisa());
            nuevo.setDocumentoIdentificativo(m.getDocumentoIdentificativo());

            // Guardamos en memoria
            return repo.save(nuevo);
        }

        // Si ya viene con ID (caso raro), lo guardamos directamente
        return repo.save(m);
    }

    /**
     * Devuelve todas las solicitudes almacenadas
     */
    public List<modelo> listar() {
        return repo.findAll();
    }

    /**
     * Obtiene una solicitud concreta por su ID
     */
    public modelo obtener(String id) {

        /*
         * Buscamos en el repositorio.
         * 
         * Si no existe, lanzamos una excepción personalizada
         * que luego se convierte en un 404.
         */
        return repo.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Solicitud no encontrada con id: " + id
                        )
                );
    }

    /**
     * Cambia el estado de una solicitud
     * respetando el flujo definido en el enunciado
     */
    public modelo cambiarEstado(String id, modelo.Estado nuevoEstado) {

        // Primero obtenemos la solicitud (o da 404)
        modelo actual = obtener(id);

        // Validamos si el cambio es legal
        validarTransicion(actual.getEstado(), nuevoEstado);

        // Si es válido, actualizamos el estado
        actual.setEstado(nuevoEstado);

        // Guardamos el cambio
        return repo.save(actual);
    }

    /**
     * Comprueba si una transición de estado es válida
     */
    private void validarTransicion(modelo.Estado actual, modelo.Estado nuevo) {

        /*
         * Reglas del sistema:
         * 
         * PENDIENTE -> APROBADA
         * PENDIENTE -> RECHAZADA
         */
        if (actual == modelo.Estado.PENDIENTE &&
                (nuevo == modelo.Estado.APROBADA || nuevo == modelo.Estado.RECHAZADA)) {
            return;
        }

        /*
         * APROBADA -> CANCELADA
         */
        if (actual == modelo.Estado.APROBADA &&
                nuevo == modelo.Estado.CANCELADA) {
            return;
        }

        /*
         * Cualquier otra combinación es inválida.
         * 
         * Lanzamos excepción que se convertirá en HTTP 400.
         */
        throw new BadRequestException(
                "Transición inválida: " + actual + " -> " + nuevo
        );
    }
}
