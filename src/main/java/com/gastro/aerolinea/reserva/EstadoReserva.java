package com.gastro.aerolinea.reserva;

/**
 * Enum que representa los posibles estados de una reserva de vuelo.
 * Se recomienda usar este enum en lugar de String para el campo "estado"
 * en ReservaDTO / ReservaEntity para mayor seguridad de tipos y evitar errores.
 */
public enum EstadoReserva {

    PENDIENTE("Pendiente"),
    CONFIRMADA("Confirmada"),
    CANCELADA("Cancelada"),
    COMPLETADA("Completada"),
    EN_VUELO("En vuelo"),
    RECHAZADA("Rechazada");

    private final String descripcion;

    EstadoReserva(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return name() + " - " + descripcion;
    }
}
