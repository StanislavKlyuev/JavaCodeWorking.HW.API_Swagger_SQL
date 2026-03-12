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
        return studentRepository.findById(id).get();
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
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

    public Collection<Student> getStudentByAge(int age) {
        return studentRepository.findAll()
                .stream()
                .filter(s -> s.getAge() == age)
                .toList();
    }
}