package com.easy.offers.domain.enums;

import java.time.LocalDate;

public enum OfferStatus {
    UPCOMING("Próxima"), // La oferta aún no comenzó
    ACTIVE("Activa"), // La oferta está vigente actualmente
    EXPIRED("Vencida"); // La oferta ya terminó

    private final String displayName;
    // Nombre legible para mostrar al usuario (UI, reportes, etc)
    // Es "final" porque no debe cambiar.

    OfferStatus(String displayName) {
        // Constructor del enum (privado implícitamente)
        // Se ejecuta una vez por cada valor del enum
        this.displayName = displayName; // Guarda el nombre amigable
    }

    public String getDisplayName() {
        // Permite obtener el nombre para mostrar
        return displayName;
    }

    // Método de lógica de negocio.
    // Determina automáticamente el estado según fechas.
    public static OfferStatus calculate(LocalDate startsAt, LocalDate endsAt) {
        LocalDate today = LocalDate.now(); // Obtiene la fecha actual del sistema
        if (today.isBefore(startsAt)) return UPCOMING; // Si hoy es antes de la fecha de inicio → la oferta aún no empezó
        else if (!today.isAfter(endsAt)) return ACTIVE; // Si hoy NO es después de la fecha de fin → la oferta sigue vigente
        else return EXPIRED; // Si ninguna de las anteriores → ya pasó la fecha final
    }
}