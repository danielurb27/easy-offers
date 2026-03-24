package com.easy.offers.domain.entity;

import com.easy.offers.domain.enums.AuditChangeType; // Enum que define si fue CREACIÓN, EDICIÓN o ELIMINACIÓN.
import jakarta.persistence.*; // Biblioteca para mapear la clase a una tabla de base de datos (JPA).
import lombok.*; // Importa Lombok para reducir código repetitivo (getters, setters, etc.).
import java.time.LocalDateTime; // Clase para manejar fecha y hora

@Entity // Indica a JPA que esta clase es una entidad y debe persistirse en la base de datos.
@Table(name = "offer_audit_log") // Mapea esta clase a la tabla "offer_audit_log"
@Getter @NoArgsConstructor @AllArgsConstructor @Builder // Lombok: getters, constructores y builder
public class OfferAuditLog {

    @Id // Define el campo como la clave primaria (Primary Key).
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID numérico autoincremental
    @Column(name = "id", updatable = false, nullable = false) // No se puede editar ni ser nulo
    private Long id;

    // RELACIONES: @ManyToOne

    @ManyToOne(fetch = FetchType.LAZY) // Relación con la oferta que sufrió el cambio.
    @JoinColumn(name = "offer_id", nullable = false) // Llave foránea hacia la tabla 'offers'.
    private Offer offer;

    @ManyToOne(fetch = FetchType.LAZY) // Relación con el usuario que realizó la acción.
    @JoinColumn(name = "changed_by", nullable = false) // Llave foránea hacia la tabla 'users'.
    private User changedBy;

    @Enumerated(EnumType.STRING) // Guarda el nombre del tipo de cambio (ej: "UPDATE", "DELETE").
    @Column(name = "change_type", nullable = false, length = 30)
    private AuditChangeType changeType;

    @Column(name = "field_changed", length = 50) // Nombre del campo que cambió (ej: "title").
    private String fieldChanged;

    @Column(name = "old_value", columnDefinition = "TEXT") // Valor que tenía el campo ANTES del cambio.
    private String oldValue;

    @Column(name = "new_value", columnDefinition = "TEXT") // Valor que tiene el campo DESPUÉS del cambio.
    private String newValue;

    @Column(name = "observation", columnDefinition = "TEXT") // Comentario extra sobre por qué se hizo el cambio.
    private String observation;

    @Column(name = "created_at", nullable = false, updatable = false) // Momento exacto del cambio.
    private LocalDateTime createdAt;

    @PrePersist // Método automático para asignar la fecha antes de insertar el registro.
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
