//AppUserRepository.java

package com.matias.orders_api.repository;

import com.matias.orders_api.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    // Buscar usuario por email
    Optional<AppUser> findByEmail(String email);

    // Verificar si existe un usuario con ese email
    boolean existsByEmail(String email);
}