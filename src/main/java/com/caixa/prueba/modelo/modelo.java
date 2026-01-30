package com.caixa.prueba.modelo;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Representa una solicitud de préstamo.
 * 
 * Es el modelo principal del sistema.
 * Contiene toda la información necesaria
 * para gestionar una petición.
 */
public class modelo {

    // Identificador único
    private String id;

    // Nombre del solicitante
    private String nombreSolicitante;

    // Importe solicitado
    private double importeSolicitado;

    // Divisa (EUR, USD, etc.)
    private String divisa;

    // Documento identificativo (DNI, NIE...)
    private String documentoIdentificativo;

    // Fecha de creación
    private LocalDateTime fechaCreacion;

    // Estado actual de la solicitud
    private Estado estado;

    /**
     * Estados posibles de una solicitud
     */
    public enum Estado {
        PENDIENTE,
        APROBADA,
        RECHAZADA,
        CANCELADA
    }

    /**
     * Constructor por defecto.
     * 
     * Genera automáticamente:
     * - ID único
     * - Fecha actual
     * - Estado inicial
     */
    public modelo() {
        this.id = UUID.randomUUID().toString();
        this.fechaCreacion = LocalDateTime.now();
        this.estado = Estado.PENDIENTE;
    }

    // ===== Getters y Setters =====

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    public double getImporteSolicitado() {
        return importeSolicitado;
    }

    public void setImporteSolicitado(double importeSolicitado) {
        this.importeSolicitado = importeSolicitado;
    }

    public String getDivisa() {
        return divisa;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    public String getDocumentoIdentificativo() {
        return documentoIdentificativo;
    }

    public void setDocumentoIdentificativo(String documentoIdentificativo) {
        this.documentoIdentificativo = documentoIdentificativo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
