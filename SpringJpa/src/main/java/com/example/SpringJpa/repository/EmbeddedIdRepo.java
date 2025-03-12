package com.example.SpringJpa.repository;

import com.example.SpringJpa.entity.association.compositeid.embedded.CustomerEmbedd;
import com.example.SpringJpa.entity.association.compositeid.embedded.EmbeddIdCustomer;
import org.springframework.data.repository.CrudRepository;

public interface EmbeddedIdRepo extends CrudRepository<CustomerEmbedd, EmbeddIdCustomer> {
}
