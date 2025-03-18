package com.example.SpringMongoDb.services;

import com.example.SpringMongoDb.documents.Student;
import com.example.SpringMongoDb.repository.DepartmentRefRepo;
import com.example.SpringMongoDb.repository.StudentRefRepo;
import com.example.SpringMongoDb.repository.StudentRepository;
import com.example.SpringMongoDb.repository.SubjectRefRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentRefRepo studentRefRepository;

    @Autowired
    private DepartmentRefRepo departmentRefRepo;

    @Autowired
    private SubjectRefRepo subjectRefRepo;

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student findById(String id) {
        return studentRepository.findById(id).get();
    }

    public List<Student> findByAll() {
        return studentRepository.findAll();
    }

    public Student update(Student student) {
        return studentRepository.save(student);
    }

    public void delete(String id) {
        studentRepository.deleteById(id);
    }

    public List<Student> findByDepartmentName(String deptName) {
        return studentRepository.findByDepartmentName(deptName);
    }

    public List<Student> findBySubjectName(String subjectName) {
        return studentRepository.findBySubjectsName(subjectName);
    }

    public com.example.SpringMongoDb.documents.reference.Student saveRefStudent(com.example.SpringMongoDb.documents.reference.Student student) {

        if (student.getDepartment() != null) {
            departmentRefRepo.save(student.getDepartment());
        }

        if (student.getSubjects() != null && student.getSubjects().size() > 0) {
            subjectRefRepo.saveAll(student.getSubjects());
        }
        return studentRefRepository.save(student);
    }

    public List<com.example.SpringMongoDb.documents.reference.Student> getByDeptId(String deptId) {
        return studentRefRepository.findByDepartmentId(deptId);
    }

    public List<com.example.SpringMongoDb.documents.reference.Student> getByName(String name) {
        return studentRefRepository.getByName(name);
    }
}
