//Customer.java
package com.matias.orders_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Un cliente está vinculado a un AppUser (cuenta de usuario)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private AppUser user;

    @Column(nullable = false, length = 180)
    private String direccion;

    @Column(nullable = false, length = 60)
    private String telefono;

    public Customer() {}

    public Customer(AppUser user, String direccion, String telefono) {
        this.user = user;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Long getId() { return id; }
    public AppUser getUser() { return user; }
    public void setUser(AppUser user) { this.user = user; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}