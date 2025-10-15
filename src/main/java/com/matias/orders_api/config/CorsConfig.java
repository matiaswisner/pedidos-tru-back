//CorsConfig.java
package com.matias.orders_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        // 🔹 Permite cualquier origen (sin usar "*")
                        .allowedOriginPatterns("*")
                        // 🔹 Métodos HTTP permitidos
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        // 🔹 Permite todos los headers
                        .allowedHeaders("*")
                        // 🔹 Habilita cookies/autenticación cruzada
                        .allowCredentials(true);
            }
        };
    }
}