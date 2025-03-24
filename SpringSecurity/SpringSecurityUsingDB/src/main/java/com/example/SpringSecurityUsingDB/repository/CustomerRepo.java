package com.example.SpringSecurityUsingDB.repository;

import com.example.SpringSecurityUsingDB.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepo extends CrudRepository<Customer, Long> {

    public Optional<Customer> findByEmail(String email);
}
