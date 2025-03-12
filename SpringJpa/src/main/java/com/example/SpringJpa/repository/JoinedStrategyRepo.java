package com.example.SpringJpa.repository;

import com.example.SpringJpa.entity.inheritancestrategy.Publication;
import org.springframework.data.repository.CrudRepository;

public interface JoinedStrategyRepo extends CrudRepository<Publication, Integer> {
}
