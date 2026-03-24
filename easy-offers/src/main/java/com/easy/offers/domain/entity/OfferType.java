package com.easy.offers.domain.entity;

import jakarta.persistence.*; // Biblioteca para mapear la clase a una tabla de base de datos (JPA).
import lombok.*; // Importa Lombok para reducir código repetitivo (getters, setters, etc.).
import java.time.LocalDateTime; // Clase para manejar fecha y hora

@Entity // Indica a JPA que esta clase es una entidad y debe persistirse en la base de datos.
@Table(name = "offer_types") // Mapea esta clase a la tabla "offer_types"
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder // Lombok: getters, setters, constructores y builder
public class OfferType {

    @Id // Define el campo como la clave primaria (Primary Key).
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID numérico autoincremental
    @Column(name = "id", updatable = false, nullable = false) // No se puede editar ni ser nulo
    private Integer id;

    @Column(name = "name", nullable = false, length = 100) // Nombre del tipo de oferta (obligatorio, máx 100 caracteres).
    private String name;

    @Column(name = "code", nullable = false, unique = true, length = 10) // Código único para evitar duplicados.
    private String code;

    @Column(name = "is_active", nullable = false) // Estado lógico; permite "borrar" la oferta sin eliminar el registro.
    private boolean isActive = true;

    @Column(name = "created_at", nullable = false, updatable = false) // Fecha técnica de creación; no se puede modificar.
    private LocalDateTime createdAt;

    @PrePersist // Evento automático: se ejecuta justo antes de que el objeto se guarde en la BD.
    protected void onCreate() {
        this.createdAt = LocalDateTime.now(); // Asigna la fecha y hora actual automáticamente.
    }
}
