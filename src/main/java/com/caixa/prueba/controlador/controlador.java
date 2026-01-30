package com.caixa.prueba.controlador;

import com.caixa.prueba.modelo.modelo;
import com.caixa.prueba.servicio.request;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST.
 * 
 * Expone los endpoints HTTP.
 * Recibe peticiones del cliente
 * y delega la lógica en el servicio.
 */
@RestController
@RequestMapping("/api/solicitudes")
public class controlador {

    // Servicio de negocio
    private final request service;

    /**
     * Inyección del servicio
     */
    public controlador(request service) {
        this.service = service;
    }

    /**
     * Crea una nueva solicitud
     * POST /api/solicitudes
     */
    @PostMapping
    public modelo crear(@RequestBody modelo body) {
        return service.crear(body);
    }

    /**
     * Lista todas las solicitudes
     * GET /api/solicitudes
     */
    @GetMapping
    public List<modelo> listar() {
        return service.listar();
    }

    /**
     * Obtiene una solicitud por ID
     * GET /api/solicitudes/{id}
     */
    @GetMapping("/{id}")
    public modelo obtener(@PathVariable String id) {
        return service.obtener(id);
    }

    /**
     * Cambia el estado de una solicitud
     * PUT /api/solicitudes/{id}/estado?estado=APROBADA
     */
    @PutMapping("/{id}/estado")
    public modelo cambiarEstado(
            @PathVariable String id,
            @RequestParam("estado") modelo.Estado estado) {

        return service.cambiarEstado(id, estado);
    }
}
