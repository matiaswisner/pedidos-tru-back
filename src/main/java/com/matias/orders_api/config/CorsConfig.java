package com.matias.orders_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Indica a Spring que esta clase es de configuración
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica esta configuración a TODAS las rutas de la API
                .allowedOriginPatterns("*") // Permite el acceso desde cualquier origen (flexible en desarrollo)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite los métodos HTTP comunes
                .allowedHeaders("*") // Permite cualquier cabecera (incluyendo la de Autorización)
                .allowCredentials(true); // <--- SOLUCIÓN: Deshabilita el manejo de credenciales para evitar el error con el comodín (*)
    }
}