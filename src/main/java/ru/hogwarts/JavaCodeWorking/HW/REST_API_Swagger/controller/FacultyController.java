package ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.model.Faculty;
import ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
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

    @GetMapping()
    public ResponseEntity getFaculties(@RequestParam(required = false) String name,
                                       @RequestParam(required = false) String color) {
        if (name != null && !name.isBlank()) {
            Collection<?> coll = facultyService.getFacultiesByName(name);
            if (coll.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faculty with name \"" + name + "\" was not found");
            }
            return ResponseEntity.ok(coll);
        }
        if (color != null && !color.isBlank()) {
            Collection<?> coll = facultyService.getFacultiesByColor(color);
            if (coll.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faculty with color \"" + color + "\" was not found");
            }
            return ResponseEntity.ok(coll);
        }
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getFacultyById(@PathVariable Long id) {
        Faculty faculty = facultyService.getFacultyById(id);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faculty with id " + id + " was not found");
        }
        return ResponseEntity.ok(faculty);
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

    @GetMapping("/student_id/{id}")
    public ResponseEntity<?> getFacultyByStudentID(@PathVariable("id") Long student_id) {
        Faculty faculty = facultyService.findFacultyByStudentId(student_id);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faculty by student_id #\"" + student_id + "\" was not found........ЗДЕСЬ НЕТ ТАКОГО СТУДЕНТА!");
        }
        return ResponseEntity.ok(faculty);
    }
}