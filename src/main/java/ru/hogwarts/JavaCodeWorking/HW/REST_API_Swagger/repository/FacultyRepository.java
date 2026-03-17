package ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Collection<Faculty> findByNameContainsIgnoreCase(String name);

    Collection<Faculty> findByColorContainsIgnoreCase(String color);
}
