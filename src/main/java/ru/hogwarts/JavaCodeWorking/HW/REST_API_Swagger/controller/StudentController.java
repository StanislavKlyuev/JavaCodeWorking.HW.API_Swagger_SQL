package ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.model.Student;
import ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
        //return ResponseEntity.ok().build();

    }

    @GetMapping("{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        Student newStudent = studentService.getStudentById(id);
        if (newStudent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student was not found");
        }
        return ResponseEntity.ok(newStudent);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllStudents() {
        Collection<Student> coll = studentService.getAllStudents();
        if (coll.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student list is empty");
        }
        return ResponseEntity.ok(coll);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateStudent(@RequestBody Student student, @PathVariable Long id) {
        Student newStudent = studentService.updateStudent(student, id);
        if (newStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student was not update");
        }
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudentById(id);
    }

    @GetMapping
    public ResponseEntity<?> getStudentsByAge(@RequestParam("age") int age) {
        Collection<Student> coll = studentService.getStudentByAge(age);
        if (coll.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with age " + age + " was not found");
        }
        return ResponseEntity.ok(coll);
    }
}