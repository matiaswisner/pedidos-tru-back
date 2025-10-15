//CustomerRequest.java

package com.matias.orders_api.dto.customer;

public class CustomerRequest {
    private String direccion;
    private String telefono;

    // --- Getters y Setters ---
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
