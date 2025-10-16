//ProductController.java
package com.matias.orders_api.controller;

import com.matias.orders_api.entity.Product;
import com.matias.orders_api.repository.BusinessRepository;
import com.matias.orders_api.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final BusinessRepository businessRepository;

    public ProductController(ProductRepository productRepository, BusinessRepository businessRepository) {
        this.productRepository = productRepository;
        this.businessRepository = businessRepository;
    }

    // 🔹 NUEVO: Listar todos los productos
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    // 🔹 Crear producto con imágenes
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestParam Long businessId, @RequestBody Product product) {
        Optional<Business> business = businessRepository.findById(businessId);
        if (business.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Limitar a 6 imágenes
        if (product.getImages() != null && product.getImages().size() > 6) {
            return ResponseEntity.badRequest().build();
        }

        product.setBusiness(business.get());
        Product saved = productRepository.save(product);
        return ResponseEntity.ok(saved);
    }

    // 🔹 Listar todos los productos de un negocio
    @GetMapping("/business/{businessId}")
    public ResponseEntity<List<Product>> getProductsByBusiness(@PathVariable Long businessId) {
        Optional<Business> business = businessRepository.findById(businessId);
        if (business.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productRepository.findByBusiness(business.get()));
    }

    // 🔹 Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updated) {
        return productRepository.findById(id).map(product -> {
            product.setName(updated.getName());
            product.setPrice(updated.getPrice());
            product.setAvailable(updated.getAvailable());
            if (updated.getImages() != null && updated.getImages().size() <= 6) {
                product.setImages(updated.getImages());
            }
            return ResponseEntity.ok(productRepository.save(product));
        }).orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 Buscar productos por nombre (ej: "pizza")
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
}