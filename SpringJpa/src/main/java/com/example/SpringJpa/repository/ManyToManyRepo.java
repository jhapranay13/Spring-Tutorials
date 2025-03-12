package com.example.SpringJpa.repository;

import com.example.SpringJpa.entity.association.manytomany.Programmer;
import org.springframework.data.repository.CrudRepository;

public interface ManyToManyRepo extends CrudRepository<Programmer, Integer> {
}
