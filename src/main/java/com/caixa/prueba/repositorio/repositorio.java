package com.caixa.prueba.repositorio;

import com.caixa.prueba.modelo.modelo;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Repositorio en memoria.
 * 
 * Simula una base de datos usando un Map.
 * No se persiste nada en disco.
 */
@Repository
public class repositorio {

    // Simulación de base de datos
    private final Map<String, modelo> data = new HashMap<>();

    /**
     * Guarda o actualiza una solicitud
     */
    public modelo save(modelo m) {

        // Usamos el ID como clave
        data.put(m.getId(), m);

        return m;
    }

    /**
     * Busca una solicitud por ID
     */
    public Optional<modelo> findById(String id) {
        return Optional.ofNullable(data.get(id));
    }

    /**
     * Devuelve todas las solicitudes
     */
    public List<modelo> findAll() {
        return new ArrayList<>(data.values());
    }
}
