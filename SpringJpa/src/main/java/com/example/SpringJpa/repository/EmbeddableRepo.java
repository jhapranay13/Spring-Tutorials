package com.example.SpringJpa.repository;

import com.example.SpringJpa.entity.Worker;
import org.springframework.data.repository.CrudRepository;

public interface EmbeddableRepo extends CrudRepository<Worker, Integer> {
}
