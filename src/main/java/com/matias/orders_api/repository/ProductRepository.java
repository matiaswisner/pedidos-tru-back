//ProductRepository.java
package com.matias.orders_api.repository;

import com.matias.orders_api.entity.Product;
import com.matias.orders_api.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByBusiness(Business business);
    List<Product> findByNameContainingIgnoreCase(String name);
}