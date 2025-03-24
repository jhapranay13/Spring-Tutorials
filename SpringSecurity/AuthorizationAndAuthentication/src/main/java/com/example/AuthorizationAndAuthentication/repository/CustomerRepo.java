package com.example.AuthorizationAndAuthentication.repository;

import com.example.AuthorizationAndAuthentication.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepo extends CrudRepository<Customer, Long> {

    public Optional<Customer> findByEmail(String email);
}
