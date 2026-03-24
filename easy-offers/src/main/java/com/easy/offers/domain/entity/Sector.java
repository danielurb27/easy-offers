package com.easy.offers.domain.entity;

import jakarta.persistence.*; // Biblioteca para mapear la clase a una tabla de base de datos (JPA).
import lombok.*; // Importa Lombok para reducir código repetitivo (getters, setters, etc.).
import java.time.LocalDateTime; // Clase para manejar fecha y hora

@Entity // Indica a JPA que esta clase es una entidad y debe persistirse en la base de datos.
@Table(name = "sectors") // Mapea esta clase a la tabla "sectors"
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder // Lombok: getters, setters, constructores y builder
public class Sector {

    @Id // Define el campo como la clave primaria (Primary Key).
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID numérico autoincremental
    @Column(name = "id", updatable = false, nullable = false) // No se puede editar ni ser nulo
    private Integer id;

    @Column(name = "code", nullable = false, unique = true, length = 10) // Código corto (ej: "S-01") único en la BD
    private String code; // Código interno del sector

    @Column(name = "name", nullable = false, length = 100) // Nombre del sector, obligatorio, max 100 caracteres
    private String name;

    @Column(name = "is_active", nullable = false) // Estado del sector (activo/inactivo), por defecto true
    private boolean isActive = true;

    @Column(name = "created_at", nullable = false, updatable = false) // Fecha de alta, inmutable tras guardarse
    private LocalDateTime createdAt;

    @PrePersist // "Hook" que se dispara automáticamente justo antes del primer guardado (INSERT)
    protected void onCreate() {
        this.createdAt = LocalDateTime.now(); // Setea la hora exacta de creación sin intervención manual
    }
}
