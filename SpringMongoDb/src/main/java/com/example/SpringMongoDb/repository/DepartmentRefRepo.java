package com.example.SpringMongoDb.repository;

import com.example.SpringMongoDb.documents.reference.Department;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepartmentRefRepo extends MongoRepository<Department, String> {
}
