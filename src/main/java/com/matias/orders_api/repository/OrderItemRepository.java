//OrderItemRepository.java
package com.matias.orders_api.repository;

import com.matias.orders_api.entity.OrderItem;
import com.matias.orders_api.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(CustomerOrder order);
}