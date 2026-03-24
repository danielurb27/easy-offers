package com.easy.offers.domain.entity;

import com.easy.offers.domain.enums.UserRole; // Importa Enum personalizado que define los roles
import jakarta.persistence.*; // Biblioteca para mapear la clase a una tabla de base de datos (JPA).
import lombok.*; // Importa Lombok para reducir código repetitivo (getters, setters, etc.).
import java.time.LocalDateTime; // Clase para manejar fecha y hora
import java.util.UUID; // Importa la clase para generar Identificadores Únicos Universales (IDs de 36 caracteres aleatorios).

@Entity // Indica a JPA que esta clase es una entidad y debe persistirse en la base de datos.
@Table(name = "users") // Mapea esta clase a la tabla "users"
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder // Lombok: getters, setters, constructores y builder
public class User {

    @Id // Define el campo como la clave primaria (Primary Key).
    @GeneratedValue(strategy = GenerationType.UUID) // Genera automáticamente un ID único tipo UUID (36 caracteres).
    @Column(name = "id", updatable = false, nullable = false) // Mapea el nombre, impide que se edite y que sea nulo.
    private UUID id;

    @Column(name = "full_name", nullable = false, length = 100) // Campo obligatorio con un límite de 100 caracteres.
    private String fullName;

    @Column(name = "username", nullable = false, unique = true, length = 150) // No permite valores repetidos en la tabla.
    private String username;

    @Column(name = "password_hash", nullable = false, length = 255) // Guarda la contraseña (hash encriptado).
    private String passwordHash;

    @Enumerated(EnumType.STRING) // Guarda el nombre del enum (ADMIN, USER) en vez de su número de índice (0, 1).
    @Column(name = "role", nullable = false, length = 20) // Rol del usuario (ADMIN / EMPLOYEE)
    private UserRole role;

    @Column(name = "is_active", nullable = false) // Define un booleano para estados activos/inactivos (por defecto true).
    private boolean isActive = true;

    @Column(name = "created_at", nullable = false, updatable = false) // Fecha de creación; el "updatable = false" impide que cambie tras crearse.
    private LocalDateTime createdAt;

    @PrePersist // Método que se ejecuta automáticamente justo antes de guardar el registro por primera vez.
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
