package com.example.JWTTokenIntro.repository;

import com.example.JWTTokenIntro.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepo extends CrudRepository<Customer, Long> {

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    // @PreAuthorize("hasAnyRole('ROLE_USER')"
    // @PreAuthorize("hasRole('ROLE_USER') and #customer.email == principal.username")
    // Similarly we have postAuthorize, preFilter, postFilter
    public Optional<Customer> findByEmail(String email);
}
