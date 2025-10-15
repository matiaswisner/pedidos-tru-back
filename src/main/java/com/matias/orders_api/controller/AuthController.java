//AuthController.java
package com.matias.orders_api.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.matias.orders_api.entity.AppUser;
import com.matias.orders_api.enums.Role;
import com.matias.orders_api.repository.AppUserRepository;
import com.matias.orders_api.service.GoogleAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final GoogleAuthService googleAuthService;
    private final AppUserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(GoogleAuthService googleAuthService,
                          AppUserRepository userRepository,
                          JwtUtil jwtUtil) {
        this.googleAuthService = googleAuthService;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/google")
    public ResponseEntity<?> loginWithGoogle(@RequestBody Map<String, String> request) {
        try {
            String idToken = request.get("idToken");
            GoogleIdToken.Payload payload = googleAuthService.verifyToken(idToken);

            if (payload == null) {
                return ResponseEntity.badRequest().body("Token inválido o expirado");
            }

            String email = payload.getEmail();
            String name = (String) payload.get("name");

            // Crear usuario si no existe
            AppUser user = userRepository.findByEmail(email)
                    .orElseGet(() -> {
                        AppUser nuevo = new AppUser();
                        nuevo.setEmail(email);
                        nuevo.setFullName(name);
                        nuevo.setRole(Role.CUSTOMER);
                        return userRepository.save(nuevo);
                    });

            // Generar JWT
            String token = jwtUtil.generateToken(user.getEmail());

            return ResponseEntity.ok(Map.of(
                    "jwt", token,
                    "email", user.getEmail(),
                    "name", user.getFullName()
            ));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al autenticar: " + e.getMessage());
        }
    }
}