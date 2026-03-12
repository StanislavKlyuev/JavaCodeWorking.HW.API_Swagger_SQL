package ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
