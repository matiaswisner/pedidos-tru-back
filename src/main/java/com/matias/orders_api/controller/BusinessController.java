//BusinessController.java
package com.matias.orders_api.controller;

import com.matias.orders_api.entity.AppUser;
import com.matias.orders_api.entity.Business;
import com.matias.orders_api.enums.BusinessCategory;
import com.matias.orders_api.repository.AppUserRepository;
import com.matias.orders_api.repository.BusinessRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/businesses")
public class BusinessController {

    private final BusinessRepository businessRepository;
    private final AppUserRepository appUserRepository;

    public BusinessController(BusinessRepository businessRepository, AppUserRepository appUserRepository) {
        this.businessRepository = businessRepository;
        this.appUserRepository = appUserRepository;
    }

    // Crear un nuevo negocio
    @PostMapping
    public ResponseEntity<Business> createBusiness(@RequestParam Long ownerId, @RequestBody Business business) {
        Optional<AppUser> owner = appUserRepository.findById(ownerId);
        if (owner.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        business.setOwner(owner.get());
        Business saved = businessRepository.save(business);
        return ResponseEntity.ok(saved);
    }

    // Obtener negocio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Business> getBusinessById(@PathVariable Long id) {
        return businessRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Listar negocios por categoría
    @GetMapping("/category/{category}")
    public List<Business> getBusinessesByCategory(@PathVariable BusinessCategory category) {
        return businessRepository.findByCategory(category);
    }

    // Listar todos los negocios
    @GetMapping
    public List<Business> getAllBusinesses() {
        return businessRepository.findAll();
    }
}