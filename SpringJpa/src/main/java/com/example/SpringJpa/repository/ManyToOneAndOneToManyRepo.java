package com.example.SpringJpa.repository;

import com.example.SpringJpa.entity.association.onetomany.Customer;
import org.springframework.data.repository.CrudRepository;

public interface ManyToOneAndOneToManyRepo extends CrudRepository<Customer, Integer> {
}
