package com.example.SpringMongoDb.repository;

import com.example.SpringMongoDb.documents.Student;
import com.example.SpringMongoDb.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

    // All the AND, OR, LIKE, Pagination, Sorting, Starts With and Like
    // are same as in JPA.

    // Getting document by SubDocument Field
    public List<Student> findByDepartmentName(String deptName);

    public List<Student> findBySubjectsName(String subjectName);
}
