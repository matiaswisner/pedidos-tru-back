package com.matias.orders_api.entity;

import com.matias.orders_api.enums.BusinessCategory;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "businesses")
public class Business {

    // 👈 CORRECCIÓN: CONSTRUCTOR VACÍO AGREGADO
    public Business() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private BusinessCategory category = BusinessCategory.OTHER;

    private String address;

    private Double lat;
    private Double lng;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private AppUser owner;

    private Boolean open = true;

    private Instant createdAt = Instant.now();

    // --- Getters y Setters --- (Mantén todos los que tenías)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    // ... todos los demás getters y setters ...
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}