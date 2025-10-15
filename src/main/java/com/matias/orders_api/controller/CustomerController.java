//CustomerController.java
package com.matias.orders_api.controller;

import com.matias.orders_api.dto.customer.CustomerRequest;
import com.matias.orders_api.dto.customer.CustomerResponse;
import com.matias.orders_api.entity.AppUser;
import com.matias.orders_api.entity.Customer;
import com.matias.orders_api.repository.AppUserRepository;
import com.matias.orders_api.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final AppUserRepository appUserRepository;

    public CustomerController(CustomerRepository customerRepository, AppUserRepository appUserRepository) {
        this.customerRepository = customerRepository;
        this.appUserRepository = appUserRepository;
    }

    // Crear cliente
    @PostMapping("/{userId}")
    public ResponseEntity<CustomerResponse> createCustomer(
            @PathVariable Long userId,
            @RequestBody CustomerRequest request) {

        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Customer customer = new Customer(user, request.getDireccion(), request.getTelefono());
        Customer saved = customerRepository.save(customer);

        CustomerResponse response = new CustomerResponse();
        response.setId(saved.getId());
        response.setNombre(user.getFullName());
        response.setEmail(user.getEmail());
        response.setDireccion(saved.getDireccion());
        response.setTelefono(saved.getTelefono());

        return ResponseEntity.ok(response);
    }

    // Listar todos los clientes
    @GetMapping
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream().map(customer -> {
            CustomerResponse resp = new CustomerResponse();
            resp.setId(customer.getId());
            resp.setNombre(customer.getUser().getFullName());
            resp.setEmail(customer.getUser().getEmail());
            resp.setDireccion(customer.getDireccion());
            resp.setTelefono(customer.getTelefono());
            return resp;
        }).collect(Collectors.toList());
    }

    // Obtener cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    CustomerResponse resp = new CustomerResponse();
                    resp.setId(customer.getId());
                    resp.setNombre(customer.getUser().getFullName());
                    resp.setEmail(customer.getUser().getEmail());
                    resp.setDireccion(customer.getDireccion());
                    resp.setTelefono(customer.getTelefono());
                    return ResponseEntity.ok(resp);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}