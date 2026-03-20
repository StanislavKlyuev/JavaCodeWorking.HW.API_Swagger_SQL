package ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> getStudentsByNameContainsIgnoreCase(String name);

    Collection<Student> getStudentsBySurnameContainsIgnoreCase(String surname);

    Collection<Student> findByAge(int age);

    Collection<Student> findByAgeBefore(int ageBefore);

    Collection<Student> findByAgeAfter(int ageAfter);

    Collection<Student> findByAgeBetween(int ageAfter, int ageBefore);

    Collection<Student> findByFaculty_Id(Long id);
}
