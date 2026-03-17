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

    @GetMapping
    public ResponseEntity<?> getStudents(@RequestParam(required = false) Integer age,
                                         @RequestParam(required = false) String name,
                                         @RequestParam(required = false) String surname,
                                         @RequestParam(required = false) Integer min,
                                         @RequestParam(required = false) Integer max,
                                         @RequestParam(required = false) Long faculty_Id) {
        if (age != null) {
            Collection<Student> coll = studentService.getStudentByAge(age);
            if (coll.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with age " + age + " was not found");
            }
            return ResponseEntity.ok(coll);
        }
        if (name != null && !name.isBlank()) {
            Collection<?> coll = studentService.getStudentsByNameContainsIgnoreCase(name);
            if (coll.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with name \"" + name + "\" was not found");
            }
            return ResponseEntity.ok(coll);
        }
        if (surname != null && !surname.isBlank()) {
            Collection<?> coll = studentService.getStudentsBySurnameContainsIgnoreCase(surname);
            if (coll.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with surname \"" + surname + "\" was not found");
            }
            return ResponseEntity.ok(coll);
        }
        if (min != null && max != null) {
            Collection<?> coll = studentService.getStudentsByAgeBetween(min, max);
            if (coll.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with age between \"" + min + "\" and \"" + max + "\" was not found");
            }
            return ResponseEntity.ok(coll);
        }

        if (min != null) {
            Collection<?> coll = studentService.getStudentsByAgeAfter(min);
            if (coll.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with age after \"" + min + "\" was not found");
            }
            return ResponseEntity.ok(coll);
        }

        if (max != null) {
            Collection<?> coll = studentService.getStudentsByAgeBefore(max);
            if (coll.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with age before \"" + max + "\" was not found");
            }
            return ResponseEntity.ok(coll);
        }

        if (faculty_Id != null) {
            Collection<?> coll = studentService.getStudentsByFaculty_Id(faculty_Id);
            if (coll.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student of faculty_id #\"" + faculty_Id + "\" was not found");
            }
            return ResponseEntity.ok(coll);
        }

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
}