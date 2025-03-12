package com.example.SpringJpa.repository;

import com.example.SpringJpa.entity.inheritancestrategy.Payment;
import org.springframework.data.repository.CrudRepository;

public interface TablePerClassStrategyRepo extends CrudRepository<Payment, Integer> {
}
