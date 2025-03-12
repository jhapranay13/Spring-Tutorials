package com.example.SpringJpa.repository;

import com.example.SpringJpa.entity.association.onetoone.Liscense;
import org.springframework.data.repository.CrudRepository;

public interface OneToOneRepo extends CrudRepository<Liscense, Integer> {
}
