package ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.model.Student;
import ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        if(!studentRepository.existsById(id)) {
            return null;
        }
        return studentRepository.findById(id).get();
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Collection<Student> getStudentsByNameContainsIgnoreCase(String name) {
        return studentRepository.getStudentsByNameContainsIgnoreCase(name);
    }

    public Collection<Student> getStudentsBySurnameContainsIgnoreCase(String surname) {
        return studentRepository.getStudentsBySurnameContainsIgnoreCase(surname);
    }

    public Collection<Student> getStudentByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> getStudentsByAgeBetween(int ageAfter, int ageBefore) {
        return studentRepository.findByAgeBetween(ageAfter, ageBefore);
    }

    public Collection<Student> getStudentsByAgeBefore(int ageBefore) {
        return studentRepository.findByAgeBefore(ageBefore);
    }

    public Collection<Student> getStudentsByAgeAfter(int ageAfter) {
        return studentRepository.findByAgeAfter(ageAfter);
    }

    public Student updateStudent(Student student, Long id) {
        if (!studentRepository.existsById(id)) {
            return null;
        }
        student.setId(id);
        return studentRepository.save(student);
    }

    public ResponseEntity<?> deleteStudentById(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with id " + id + " was not found");
    }

    public Collection<Student> getStudentsByFaculty_Id(Long facultyId) {
        return studentRepository.getStudentsByFaculty_Id(facultyId);
    }
}