package ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.model.Faculty;
import ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.model.Student;
import ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.repository.FacultyRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class FacultyService {

    @Autowired
    private final FacultyRepository facultyRepository;
    @Autowired
    private StudentService studentService;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(Long id) {
        if (!facultyRepository.existsById(id)) {
            return null;
        }
        return facultyRepository.findById(id).get();
    }

    public Collection<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Collection<Faculty> getFacultiesByName(String name) {
        return facultyRepository.findByNameContainsIgnoreCase(name);
    }

    public Collection<Faculty> getFacultiesByColor(String color) {
        return facultyRepository.findByColorContainsIgnoreCase(color);
    }

    public Faculty updateFaculty(long id, Faculty faculty) {
        if (!facultyRepository.existsById(id)) {
            return null;
        }
        faculty.setId(id);
        return facultyRepository.save(faculty);
    }

    public ResponseEntity<?> deleteFacultyById(Long id) {
        if (facultyRepository.existsById(id)) {
            facultyRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faculty with id " + id + " was not found");
    }

    public Faculty findFacultyByStudentId(long studentId) {
        Optional<Student> optional = Optional.of(studentService.getStudentById(studentId));
        if (optional.isEmpty()) {
            return null;
        }
        return optional.get().getFaculty();
    }
}