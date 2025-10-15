//CustomerOrderRepository.java
package com.matias.orders_api.repository;

import com.matias.orders_api.entity.CustomerOrder;
import com.matias.orders_api.entity.AppUser;
import com.matias.orders_api.entity.Business;
import com.matias.orders_api.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
    List<CustomerOrder> findByCustomer(AppUser customer);
    List<CustomerOrder> findByBusiness(Business business);
    List<CustomerOrder> findByStatus(OrderStatus status);
}