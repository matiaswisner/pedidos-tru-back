//PaymentMethod.java
package com.matias.orders_api.enums;

public enum PaymentMethod {
    EFECTIVO,        // 💵 Pago en efectivo
    CASH,            // 💵 Alias en inglés (por compatibilidad con código previo)
    TARJETA,         // 💳 Pago con tarjeta
    CARD,            // 💳 Alias en inglés
    TRANSFERENCIA,   // 💸 Transferencia bancaria
    TRANSFER,        // 💸 Alias en inglés
    MERCADO_PAGO,    // 🟣 Pago con Mercado Pago
    MP               // 🟣 Alias corto
}