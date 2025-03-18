package com.example.SpringMongoDb.repository;

import com.example.SpringMongoDb.documents.reference.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubjectRefRepo extends MongoRepository<Subject, String> {
}
