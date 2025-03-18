package com.example.SpringMongoDb.activiry;

import com.example.SpringMongoDb.documents.Student;
import com.example.SpringMongoDb.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentSeruice;

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentSeruice.save(student);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") String id) {
        return studentSeruice.findById(id);
    }

    @GetMapping
    public List<Student> getStudent() {
        return studentSeruice.findByAll();
    }

    @PutMapping
    public Student updateStudent(@RequestBody Student student) {
        return studentSeruice.update(student);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String id) {
        studentSeruice.delete(id);
        return "Deleted " + id;
    }

    @GetMapping("/dept")
    public List<Student> findByDepartmentName(@RequestParam String deptName) {
        return studentSeruice.findByDepartmentName(deptName);
    }

    @GetMapping("/subject")
    public List<Student> findBySubjectName(@RequestParam String subjectName) {
        return studentSeruice.findBySubjectName(subjectName);
    }

    @PostMapping("/ref")
    public com.example.SpringMongoDb.documents.reference.Student saveRefStudent(@RequestBody com.example.SpringMongoDb.documents.reference.Student student) {
        return studentSeruice.saveRefStudent(student);
    }

    @GetMapping("/ref")
    public List<com.example.SpringMongoDb.documents.reference.Student> getByDeptId(@RequestParam String deptId) {
        return studentSeruice.getByDeptId(deptId);
    }

    @GetMapping("/qry")
    public List<com.example.SpringMongoDb.documents.reference.Student> getByName(@RequestParam String name) {
        return studentSeruice.getByName(name);
    }
}
