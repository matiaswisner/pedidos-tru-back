//WebConfig.java
package com.matias.orders_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // Especificá el origen real del frontend
                // Si el front corre en el mismo host: http://localhost:5555
                // o si corre en otra máquina: http://192.168.100.4:5555
                .allowedOrigins("http://localhost:5555", "http://192.168.100.4:5555")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                // credenciales desactivaadas para usar "*" como origen
                .allowCredentials(false)
                .maxAge(3600);
    }
}