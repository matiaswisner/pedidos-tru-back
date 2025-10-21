//Product.java
package com.matias.orders_api.entity;

import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "products")
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 1000)
    private String description;  // 👉 Nueva descripción del producto

    private Double price;

    private Boolean available = true;

    private Instant createdAt = Instant.now();

    // Relación: un negocio tiene muchos productos
    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    // Relación con imágenes (máximo 6)
    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<String> images = new ArrayList<>();

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Business getBusiness() { return business; }
    public void setBusiness(Business business) { this.business = business; }

    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }
}