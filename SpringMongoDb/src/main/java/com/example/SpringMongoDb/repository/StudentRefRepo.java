package com.example.SpringMongoDb.repository;

import com.example.SpringMongoDb.documents.reference.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentRefRepo extends MongoRepository<Student, String> {
    public List<Student> findByDepartmentId(String deptId);

    @Query("{ \"name\": \"?0\" }")  // 0 after question mark is 0 based index of method argument
    List<Student> getByName(String name);
}
