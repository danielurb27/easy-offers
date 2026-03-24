package com.easy.offers.domain.exception;
// Paquete de excepciones del dominio.

// Excepción personalizada para reglas de negocio.
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
        // Llama al constructor de RuntimeException
        // Guarda el mensaje de error
    }
}
