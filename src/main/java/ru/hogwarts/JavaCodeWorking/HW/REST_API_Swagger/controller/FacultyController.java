package ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.model.Faculty;
import ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity createFaculty(@RequestBody Faculty faculty) {
        Faculty newFaculty = facultyService.createFaculty(faculty);
        if (newFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Faculty was not created");
        }
        return ResponseEntity.ok(newFaculty);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getFacultyById(@PathVariable Long id) {
        Faculty faculty = facultyService.getFacultyById(id);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faculty with id " + id + " was not found");
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/student_id/{id}")
    public ResponseEntity<?> getFacultyByStudentID(@PathVariable("id") Long student_id) {
        Faculty faculty = facultyService.findFacultyByStudentId(student_id);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faculty by student_id #\"" + student_id + "\" was not found........ЗДЕСЬ НЕТ ТАКОГО СТУДЕНТА!");
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("name")
    public ResponseEntity<?> getFacultiesByName(@RequestParam String name) {
        Collection<?> coll = facultyService.getFacultiesByName(name);
        if (coll.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faculty with name \"" + name + "\" was not found");
        }
        return ResponseEntity.ok(coll);
    }

    @GetMapping("color")
    public ResponseEntity<?> getFacultyByStudentID(@RequestParam String color) {
        Collection<?> coll = facultyService.getFacultiesByColor(color);
        if (coll.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faculty with color \"" + color + "\" was not found");
        }
        return ResponseEntity.ok(coll);
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllFaculties() {
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

    @PutMapping("{id}")
    public ResponseEntity updateFaculty(@RequestBody Faculty faculty, @PathVariable Long id) {
        Faculty newFaculty = facultyService.updateFaculty(id, faculty);
        if (newFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Faculty was not updated");
        }
        return ResponseEntity.ok(newFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteFacultyByID(@PathVariable Long id) {
        return facultyService.deleteFacultyById(id);
    }
}