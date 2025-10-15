//PaymentService.java
package com.matias.orders_api.service;

import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.matias.orders_api.dto.payment.PaymentRequest;
import com.matias.orders_api.entity.OrderItem;
import com.matias.orders_api.entity.Payment;
import com.matias.orders_api.enums.PaymentMethod;
import com.matias.orders_api.repository.OrderItemRepository;
import com.matias.orders_api.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(PaymentRequest request) throws MPException, MPApiException {
        // 1) EFECTIVO: no se crea preferencia en MP
        PaymentMethod metodo = PaymentMethod.valueOf(request.getMetodo().toUpperCase());
        if (metodo == PaymentMethod.EFECTIVO) {
            Payment cash = new Payment();
            cash.setMetodo(PaymentMethod.EFECTIVO);
            cash.setEstado("pendiente");
            cash.setMonto(request.getMonto());
            cash.setDeliveryAccount(request.getDeliveryAccount());
            return paymentRepository.save(cash);
        }

        // 2) TRANSFERENCIA / TARJETA: se crea preferencia en MP
        OrderItem orderItem = orderItemRepository.findById(request.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("No existe OrderItem con id " + request.getOrderId()));

        BigDecimal unitPrice = BigDecimal.valueOf(orderItem.getProduct().getPrice());
        int quantity = orderItem.getQuantity();

        PreferenceItemRequest itemReq = PreferenceItemRequest.builder()
                .title(orderItem.getProduct().getName())
                .quantity(quantity)
                .unitPrice(unitPrice)          // ← MP v2 requiere BigDecimal
                .currencyId("ARS")
                .build();

        PreferenceRequest prefReq = PreferenceRequest.builder()
                .items(List.of(itemReq))
                .build();

        Preference pref = new PreferenceClient().create(prefReq);

        Payment mp = new Payment();
        mp.setMetodo(metodo);                       // TRANSFERENCIA o TARJETA
        mp.setEstado("pendiente");
        mp.setMonto(unitPrice.multiply(BigDecimal.valueOf(quantity)).doubleValue());
        mp.setPreferenceId(pref.getId());
        mp.setDeliveryAccount(request.getDeliveryAccount());
        // Si tu entidad Payment tiene relación con OrderItem, descomentá:
        // mp.setOrderItem(orderItem);

        return paymentRepository.save(mp);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}