//Payment.java
package com.matias.orders_api.entity;

import jakarta.persistence.*;
import com.matias.orders_api.enums.PaymentMethod;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentMethod metodo;   // EFECTIVO / TRANSFERENCIA / TARJETA

    private String estado;          // pendiente / aprobado / rechazado
    private Double monto;
    private String paymentId;
    private String preferenceId;
    private String fechaPago;

    @OneToOne
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem;

    private String deliveryAccount; // Cuenta destino del delivery

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public PaymentMethod getMetodo() { return metodo; }
    public void setMetodo(PaymentMethod metodo) { this.metodo = metodo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }

    public String getPreferenceId() { return preferenceId; }
    public void setPreferenceId(String preferenceId) { this.preferenceId = preferenceId; }

    public String getFechaPago() { return fechaPago; }
    public void setFechaPago(String fechaPago) { this.fechaPago = fechaPago; }

    public OrderItem getOrderItem() { return orderItem; }
    public void setOrderItem(OrderItem orderItem) { this.orderItem = orderItem; }

    public String getDeliveryAccount() { return deliveryAccount; }
    public void setDeliveryAccount(String deliveryAccount) { this.deliveryAccount = deliveryAccount; }
}