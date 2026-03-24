package com.easy.offers.domain.entity;

import com.easy.offers.domain.enums.OfferStatus; // Enum para manejar los estados (ACTIVA, EXPIRADA, etc.)
import jakarta.persistence.*; // Biblioteca para mapear la clase a una tabla de base de datos (JPA).
import lombok.*; // Importa Lombok para reducir código repetitivo (getters, setters, etc.).
import java.time.LocalDate; // Para fechas sin hora (solo día/mes/año)
import java.util.UUID; // Importa la clase para generar Identificadores Únicos Universales (IDs de 36 caracteres aleatorios).

@Entity // Indica a JPA que esta clase es una entidad y debe persistirse en la base de datos.
@Table(name = "offers") // Mapea esta clase a la tabla "offers"
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder // Lombok: getters, setters, constructores y builder
public class Offer extends BaseEntity {
    // Hereda createdAt y updatedAt automáticamente

    @Id // Define el campo como la clave primaria (Primary Key).
    @GeneratedValue(strategy = GenerationType.UUID) // Genera automáticamente un ID único tipo UUID (36 caracteres).
    @Column(name = "id", updatable = false, nullable = false) // Mapea el nombre, impide que se edite y que sea nulo.
    private UUID id;

    @Column(name = "title", nullable = false, length = 200) // Título de la oferta
    private String title;

    @Column(name = "description", columnDefinition = "TEXT") // "TEXT" permite descripciones largas (más de 255 caracteres)
    private String description;

    // RELACIONES: @ManyToOne (Muchas ofertas pertenecen a UN solo...)

    @ManyToOne(fetch = FetchType.LAZY) // Carga perezosa: no trae el objeto de la BD a menos que lo pidas
    @JoinColumn(name = "offer_type_id", nullable = false) // Llave foránea que apunta a OfferType
    private OfferType offerType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sector_id", nullable = false) // Llave foránea que apunta a Sector
    private Sector sector;

    @Column(name = "starts_at", nullable = false) // Fecha de inicio de la oferta
    private LocalDate startsAt;

    @Column(name = "ends_at", nullable = false) // Fecha de fin de la oferta
    private LocalDate endsAt;

    // AUDITORÍA DE USUARIOS

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false, updatable = false) // Quién creó la oferta (no se puede cambiar)
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by") // Quién hizo la última modificación
    private User updatedBy;

    // LÓGICA DE NEGOCIO (No se guarda en la base de datos)

    @Transient // Indica a JPA que ignore este método; no existe una columna "status" en la BD
    public OfferStatus calculateStatus() { // Calcula estado dinámicamente (UPCOMING, ACTIVE, EXPIRED)
        return OfferStatus.calculate(this.startsAt, this.endsAt); // Calcula el estado comparando fechas
    }

    @Transient // Solo para uso en código Java, no en la tabla
    public boolean isCurrentlyActive() {
        return calculateStatus() == OfferStatus.ACTIVE; // Indica si está activa ahora
    }

    @Transient // Solo para uso en código Java
    public boolean isExpired() {
        return calculateStatus() == OfferStatus.EXPIRED; // Indica si ya venció
    }
}
