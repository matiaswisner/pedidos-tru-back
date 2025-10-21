package com.matias.orders_api.controller;

import com.matias.orders_api.entity.AppUser;
import com.matias.orders_api.enums.BusinessCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor; // Necesario para JPA
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "businesses")
public class BusinessController {



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

    // NOTA: Con Lombok, ya no necesitas los bloques de Getters y Setters
}