//PaymentRequest.java
package com.matias.orders_api.dto.payment;

public class PaymentRequest {
    private Long orderId;
    private String metodo;          // efectivo / transferencia / tarjeta
    private Double monto;
    private String descripcion;
    private String deliveryAccount; // cuenta destino (email o alias del delivery)

    // --- Getters y Setters ---
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDeliveryAccount() {
        return deliveryAccount;
    }

    public void setDeliveryAccount(String deliveryAccount) {
        this.deliveryAccount = deliveryAccount;
    }
}