//SecurityConfig.java
package com.matias.orders_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 🔹 Habilitar CORS
                .cors(cors -> cors.configurationSource(request -> new CorsConfiguration()))
                // 🔹 Desactivar CSRF en desarrollo
                .csrf(csrf -> csrf.disable())
                // 🔹 Permitir iframes (para consola H2)
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                // 🔹 Permitir todas las rutas sin autenticación (modo desarrollo)
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/**").permitAll()
                        // 🔒 Cuando se active JWT, se reemplazará por:
                        // .requestMatchers("/auth/**", "/public/**").permitAll()
                        // .anyRequest().authenticated()
                );

        // TODO: cuando agregues JWT:
        // http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        // http.authenticationProvider(authenticationProvider);

        return http.build();
    }

    // 🔹 CORS de bajo nivel (compatible con allowCredentials)
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("*")); // localhost, red local, apps móviles
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type", "Accept"));
        config.setAllowCredentials(true);
        config.setMaxAge(3600L); // 1 hora de caché

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    // 🔹 Exponer AuthenticationManager para futuras configuraciones JWT/OAuth2
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}