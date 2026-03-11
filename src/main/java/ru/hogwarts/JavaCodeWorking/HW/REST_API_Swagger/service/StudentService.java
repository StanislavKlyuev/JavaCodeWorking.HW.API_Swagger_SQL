package ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {

    private final Map<Long, Student> students = new HashMap<>();
    private Long id = 0L;

    public Student createStudent(Student student) {
        if (student.getName() == null || student.getName().isBlank() || student.getAge() == 0) {
            return null;
        }
        student.setId(++id);
        students.put(student.getId(), student);
        return student;
    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }

    public Student getStudent(Long id) {
        return students.get(id);
    }

    public Student updateStudent(Student student, Long id) {
        if (!students.containsKey(id) || student.getName() == null || student.getName().isBlank() || student.getAge() == 0) {
            return null;
        }
        student.setId(id);
        return students.put(id, student);
    }

    public Student deleteStudent(Long id) {
        return students.remove(id);
    }

    public Collection<Student> getStudentByAge(int age) {
        return students.values()
                .stream()
                .filter(s -> s.getAge() == age)
                .toList();

    }
}