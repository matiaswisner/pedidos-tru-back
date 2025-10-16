package com.matias.orders_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    // Mapea la ruta raíz principal
    @GetMapping("/")
    public String home() {
        return "Servidor de Orders API funcionando. Rutas disponibles: /api/businesses, /api/products";
    }
}