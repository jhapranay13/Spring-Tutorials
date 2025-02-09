package com.springrestandcloud.activity;

import com.springrestandcloud.beans.Student;
import com.springrestandcloud.beans.User;
import com.springrestandcloud.exceptions.ErrorDetails;
import com.springrestandcloud.exceptions.ResourceNotFoundException;
import com.springrestandcloud.services.DemoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// http://localhost:8080/swagger-ui/index.html#/

@Tag(
        name = "CRUD API FOR Student",
        description = "Some description for API"

)
@RestController
@RequestMapping(value = "root")
public class StudentActivity {
    List<Student> studentList = new ArrayList<>();

    @Autowired
    DemoService demoService;

    public StudentActivity() {
        studentList.add(new Student(1, "Pranay", "Jha"));
        studentList.add(new Student(2, "Sam", "Makena"));
        studentList.add(new Student(3, "Adam", "Merek"));
        studentList.add(new Student(2, "Tom", "Riddle"));
    }

    @Operation(
            summary = "Some API summary",
            description = "API description"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Response Description",
            content = @Content(
                    schema = @Schema(implementation = Student.class)
            )
    )
    @GetMapping("student")
    public Student getStudent() {
        return studentList.get(0);
    }

    @Operation(
            summary = "Some API summary",
            description = "API description"
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Response Description",
                    content = @Content(
                            schema = @Schema(implementation = List.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorised Description",
                    content = @Content(
                            schema = @Schema(implementation = List.class)
                    )
            )
        }
    )
    @GetMapping("student-all")
    public List<Student> getAllStudent() {
        return studentList;
    }

   /* @GetMapping("student/{id}")
    public Student getStudentPathVariable(@PathVariable  int id) {
        return new Student(id, "Pranay", "Jha");
    }*/

    @GetMapping("student/{id}")
    public Student getStudentPathVariable(@PathVariable("id")  int studentId) {
        return new Student(studentId, "Pranay", "Jha");
    }

    @GetMapping("student/query")
    public Student getStudentRequestParam(@RequestParam("id")  int studentId, @RequestParam String firstName) {
        return new Student(studentId, firstName, "Jha");
    }

    /*@PostMapping("student")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student stu) {
        Student student =  new Student(stu.getId(), stu.getFirstName(), stu.getLastName());
        studentList.add(student);
        return student;
    }*/

    /*@PostMapping("student")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student stu) {
        Student student =  new Student(stu.getId(), stu.getFirstName(), stu.getLastName());
        studentList.add(student);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }*/

    /*@PostMapping("student")
    public ResponseEntity<Student> createStudent(@RequestBody Student stu) {
        Student student =  new Student(stu.getId(), stu.getFirstName(), stu.getLastName());
        studentList.add(student);
        return ResponseEntity.ok(student);
    }*/

    @PostMapping("student")
    public ResponseEntity<Student> createStudent(@RequestBody Student stu) {
        Student student =  new Student(stu.getId(), stu.getFirstName(), stu.getLastName());
        studentList.add(student);
        return ResponseEntity.ok().header("custom-header", "Pranay").body(student);
    }

    @GetMapping("serviceforexception")
    public ResponseEntity<Student> getService1() {
        demoService.serviceForException();
        return ResponseEntity.ok(studentList.get(0));
    }

    @GetMapping("serviceforglobalexception")
    public ResponseEntity<Student> getServiceGlobalSxception() {
        demoService.serviceForGlobalException();
        return ResponseEntity.ok(studentList.get(0));
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ErrorDetails> localExceptionHandler(ResourceNotFoundException ex, WebRequest req) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                req.getDescription(false), "User NOT FOUND");
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @PostMapping("user")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
