package ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
