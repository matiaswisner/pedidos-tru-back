//SwaggerConfig
package com.matias.orders_api.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI ordersApiOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Orders API")
                        .description("API para gestión de pedidos e integración con Mercado Pago")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Matías Wisner")
                                .email("matiaswisner@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositorio del proyecto en GitHub")
                        .url("https://github.com/matiaswisner/pedidos-api"));
    }
}