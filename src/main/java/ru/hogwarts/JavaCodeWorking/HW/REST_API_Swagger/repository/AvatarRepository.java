package ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    Optional<Avatar> findByStudentId(Long studentId);

}
