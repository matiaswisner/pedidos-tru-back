//UserController

package com.matias.orders_api.controller;

import com.matias.orders_api.entity.AppUser;
import com.matias.orders_api.repository.AppUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final AppUserRepository appUserRepository;

    public UserController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    // ✅ Crear usuario con control de errores
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody AppUser user) {
        try {
            // ⚠️ Validar que venga el email
            if (user.getEmail() == null || user.getEmail().isBlank()) {
                return ResponseEntity.badRequest().body("El campo 'email' es obligatorio.");
            }

            // ⚠️ Desactivar temporalmente la validación si el mètodo no existe o falla
            if (appUserRepository.existsByEmail(user.getEmail())) {
                return ResponseEntity.badRequest().body("Ya existe un usuario con ese email.");
            }

            // Guardar usuario
            AppUser saved = appUserRepository.save(user);
            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            // 📜 Mostrar en consola el error real
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Error al crear el usuario: " + e.getMessage());
        }
    }

    // ✅ Listar todos los usuarios
    @GetMapping
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    // ✅ Buscar usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        return appUserRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!appUserRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        appUserRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}