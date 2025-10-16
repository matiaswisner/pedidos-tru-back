//BusinessRepository.java
package com.matias.orders_api.repository;

import com.matias.orders_api.controller.Business;
import com.matias.orders_api.enums.BusinessCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
    // Buscar negocios por categoría
    List<Business> findByCategory(BusinessCategory category);
}