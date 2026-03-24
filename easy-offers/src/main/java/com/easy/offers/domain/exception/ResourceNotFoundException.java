package com.easy.offers.domain.exception;
// Excepción para cuando un recurso solicitado no existe.

// Hereda de BusinessException → sigue siendo error de negocio.
public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException(String resource, Object id) {
        super(resource + " con id '" + id + "' no encontrado");
        // Construye un mensaje claro y reutilizable.
        // Ejemplo:
        // "Usuario con id '123' no encontrado"
        // "Offer con id 'abc' no encontrado"
    }
}
