package com.easy.offers.domain.entity;

import jakarta.persistence.*; // Biblioteca para mapear la clase a una tabla de base de datos (JPA).
import lombok.*; // Importa Lombok para reducir código repetitivo (getters, setters, etc.).
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Getter
@Setter
// Lombok genera getters y setters automáticamente

@MappedSuperclass
// Indica que esta clase NO es una tabla,
// pero sus campos se heredan a entidades hijas

@EntityListeners(AuditingEntityListener.class)
// Activa auditoría automática de Spring Data JPA

public abstract class BaseEntity {

    @CreatedDate // Se completa automáticamente al crear el registro
    @Column(name = "created_at", nullable = false, updatable = false) // No puede ser null ni modificarse luego
    private LocalDateTime createdAt;

    @LastModifiedDate // Se actualiza automáticamente cuando se modifica la entidad
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
