package com.example.SpringJpa.repository;

import com.example.SpringJpa.entity.association.compositeid.idclass.CustomerId;
import com.example.SpringJpa.entity.association.compositeid.idclass.CustomerWithIdClass;
import org.springframework.data.repository.CrudRepository;

public interface CustomerIdClassRepo extends CrudRepository<CustomerWithIdClass, CustomerId> {
}
