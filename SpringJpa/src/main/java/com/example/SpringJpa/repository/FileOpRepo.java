package com.example.SpringJpa.repository;

import com.example.SpringJpa.entity.FileOp;
import org.springframework.data.repository.CrudRepository;

public interface FileOpRepo extends CrudRepository<FileOp, Integer> {
}
