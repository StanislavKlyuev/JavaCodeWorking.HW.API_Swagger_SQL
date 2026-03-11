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

    @GetMapping("/all")
    public ResponseEntity<?> getAllFaculties() {
        Collection<Faculty> coll = facultyService.getAllFaculties();
        if (coll.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faculty list is empty");
        }
        return ResponseEntity.ok(coll);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.getFaculty(id);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faculty with id " + id + " was not found");
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public ResponseEntity<?> createFaculty(@RequestBody Faculty faculty) {
        Faculty newFaculty = facultyService.createFaculty(faculty);
        if (newFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Faculty was not created");
        }
        return ResponseEntity.ok(newFaculty);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateFaculty(@RequestBody Faculty faculty, @PathVariable Long id) {
        Faculty newFaculty = facultyService.updateFaculty(id, faculty);
        if  (newFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Faculty was not updated");
        }
        return ResponseEntity.ok(newFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.deleteFaculty(id);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faculty with id " + id + " was not found");
        }
        return new ResponseEntity<>(faculty, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getFacultiesByColor(@RequestParam("color") String color) {
        Collection<Faculty> coll = facultyService.getFacultiesByColor(color);
        if (coll == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no such color");
        }
        if (coll.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faculty was not found");
        }
        return ResponseEntity.ok(coll);
    }
}
