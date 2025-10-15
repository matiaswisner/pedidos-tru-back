//MpSdkConfig
package com.matias.orders_api.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import com.mercadopago.MercadoPagoConfig; // SDK v2.x

@Configuration
public class MpSdkConfig {

    @PostConstruct
    public void init() {
        String token = System.getenv("MP_ACCESS_TOKEN");
        if (token == null || token.isBlank()) {
            System.err.println("⚠️ MP_ACCESS_TOKEN no definido en el entorno.");
            return;
        }
        try {
            MercadoPagoConfig.setAccessToken(token);
            System.out.println("✅ Mercado Pago SDK inicializado.");
        } catch (Exception e) {
            System.err.println("❌ Error inicializando MP SDK: " + e.getMessage());
        }
    }
}